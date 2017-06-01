package com.yc.news.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

import oracle.sql.CLOB;

public class DbHelper {
	private static DataSource dataSource;
	static{
		try {
			//发布使用
			//Context context = new  InitialContext();  //创建一个上下文件对象
			//dataSource = (DataSource) context.lookup("java:comp/env/jdbc/jspJNDI");
			
			
			//开发中测试使用
			Properties props = new Properties();
			props.load(DbHelper.class.getClassLoader().getResourceAsStream("db.properties"));
			dataSource = BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取数据库连接对象
	public static Connection getConn(){
		Connection con = null;
		if (dataSource != null){
			try {
				con = dataSource.getConnection();   //在数据源中取到一个连接
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		return con;
	}
	
	//关闭连接对象
	public static void closeAll(ResultSet rs,Statement stmt,Connection conn ){
		//关闭结果集对象
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//关闭语句对象
		if(null!=stmt){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭数据库连接对象
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void closeAll(ResultSet rs,PreparedStatement pstmt,Connection conn ){
		//关闭结果集对象
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//关闭语句对象
		if(null!=pstmt){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭数据库连接对象
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 设置参数的方法
	 * @param pstmt 预编译对象
	 * @param params 传入的设置值的集合
	 * @throws SQLException 
	 */
	private static void setParams(PreparedStatement pstmt,Object...params) throws SQLException{
		if(null!=params&&params.length>0){
			for(int i=0;i<params.length;i++){
				if(params[i] instanceof Date){
					pstmt.setTimestamp(i+1,new Timestamp(((Date)params[i]).getTime()));
				}else{
					pstmt.setObject(i+1, params[i]);    //params存储值的顺序与 ?的顺序一样
				}
				//pstmt.setString(i+1, params[i].toString());//params存储值的顺序与 ?的顺序一样
			}
		}
	}
	
	/**
	 * 增删改
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static int doUpdate(String sql,Object...params) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		int result =-1;
		
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParams(pstmt, params);
			result =pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	public static int doUpdate(List<String> sqls, Object[]...params) throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt =null;
		int result =-1;
		try {
			conn = getConn();
			conn.setAutoCommit(false);
			if(null!= sqls && sqls.size()>0){
//				循环sql语句
				for(int i=0;i<sqls.size();i++){
					String sql=sqls.get(i);
					pstmt =conn.prepareStatement(sql);
//					给当前sql语句设置
					setParams(pstmt, params[i]);
					result +=pstmt.executeUpdate();
				}
			}
//		提交事物
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
//			 回滚事物
			result=0;
			conn.rollback();
		} finally {
//			回复现场
			conn.setAutoCommit(true);
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	@SuppressWarnings("null")
	public static int updateImg(String sql,int id ,File file) throws FileNotFoundException{
		FileInputStream in = new FileInputStream(file);
		Connection conn =null;
		PreparedStatement pstmt =null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setBinaryStream(1, in,(int)file.length());
			pstmt.setInt(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
		} finally {
			closeAll(null, pstmt, conn);
		}
		
		return result;
	}
	
	/**
	 * 查询多条记录
	 * @param sql
	 * @param params
	 * @return 
	 * @throws SQLException 
	 */
	public static List<Map<String , Object>> findMultiObject(String sql,Object...params) throws SQLException{
		List<Map<String , Object>> list =new ArrayList<Map<String ,Object>>();
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		Map<String, Object> map =null;
		try {
			conn =getConn();
			pstmt =conn.prepareStatement(sql);
			setParams(pstmt, params);
			rs =pstmt.executeQuery();
			List<String > columnNames =getAllColumnNames(rs);//获取结果中的所有列表
			while(rs.next()){
				map = new HashMap<String,Object>();
				for(String cn:columnNames){//循环列名，将列表作用map的键，根据列表获取到每个列的值
					map.put(cn, rs.getObject(cn));
				}
				list.add(map);
			}
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return list;
	}
	
	/**
	 * 查询单条记录   select * from 表名 where id = 1
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static Map<String,Object> findSingleObject(String sql, Object...params) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,Object> map = null;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);
			rs = pstmt.executeQuery();
			List<String> columnNames = getAllColumnNames(rs); //获取结果集中的所有列表
			while(rs.next()){
				map = new HashMap<String,Object>();
				for(String cn: columnNames){	//循环列名，将列表作用Map的键，根据列表获取到每个列的值
					map.put(cn, rs.getObject(cn));
				}
			}
		}finally{
			closeAll(rs, pstmt, conn);
		}
		return map;
	}
	
	/**
	 * 根据结果集对象获取所有的列名 存在一个list集合中 jdbc2.0取元数据
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static List<String> getAllColumnNames(ResultSet rs ) throws SQLException{
		List<String> columnNames =new ArrayList<String>();
		if(null !=rs){
			for(int i=0 ;i<rs.getMetaData().getColumnCount();i++){
				columnNames.add(rs.getMetaData().getColumnName(i+1));
			}
		}
		return columnNames;
	}

	public static<T> T get(Class<T> clazz, String sql, Object...params) throws SQLException {
		return mapping2Obj(clazz,findSingleObject(sql, params));
	}
	
	public static<T> List<T> list(Class<T> clazz, String sql,Object...params) throws SQLException {
		List<Map<String,Object>> results=findMultiObject(sql,params);
		
		List<T> ts=null;
		if (results!=null) {
			ts = new ArrayList<T>();
			for (Map<String, Object> result : results) {
				ts.add(mapping2Obj(clazz, result));
			} 
		}
		return ts;
	}
	
	private static <T> T mapping2Obj(Class<T> clazz,Map<String,Object> temps){
		
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		Method[] ms = clazz.getDeclaredMethods(); //取到所有的方法
		for (Method m : ms) {
			String mn = m.getName();  //取到方法名
			if(mn.startsWith("set")){
				String pt = m.getParameterTypes()[0].getName();  //取到set方法的参数类型
				Object obj = temps.get(mn.replace("set", "").toUpperCase()); //取到set方法对应数据库字段的值
				try {
					if(obj != null){
						if (pt.endsWith("int") || pt.endsWith("Integer")) {
							m.invoke(t, ((BigDecimal)obj).intValue());
						} else if (pt.endsWith("double") || pt.endsWith("Double")) {
							m.invoke(t, ((BigDecimal)obj).doubleValue());
						} else if (pt.endsWith("java.util.Date")) {
							//m.invoke(t, (Timestamp)obj);                             //11g
							m.invoke(t, new Date(((java.sql.Date)obj).getTime()));//10g
						}else{
							if(obj.getClass().getName().endsWith("CLOB")){
								obj=clobToString((Clob)obj);
							}
							m.invoke(t, obj);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return t;
	}
	
	public static String clobToString(Clob clob) {
        String reString = "";
        Reader is = null;
        try {
            is = clob.getCharacterStream();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 得到流
        BufferedReader br = new BufferedReader(is);
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        while (s != null) {
            //执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            sb.append(s);
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reString = sb.toString();
        return reString;
    }
}
