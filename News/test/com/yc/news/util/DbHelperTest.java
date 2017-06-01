package com.yc.news.util;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;

public class DbHelperTest {

	@Test
	public void test() {
		Connection con=DbHelper.getConn();
		LogManager.getLogger().debug("链接成功");
	}
	


}
