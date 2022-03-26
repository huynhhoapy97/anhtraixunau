package vn.com.anhtraixunau.repositories;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import vn.com.anhtraixunau.connections.OracleConnection;
import vn.com.anhtraixunau.models.StaffAccount;

public class StaffAccountDAOImpl implements StaffAccountDAO {
	private OracleConnection oracleConnection;
	private ResultSet resultSet;
	private String query;
	
	@Override
	public StaffAccount getStaffAccountByStaffId(int staffId) {
		StaffAccount staffAccount = new StaffAccount();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_account_getbystaffid(?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_staffid", staffId);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			
			oracleConnection.getCallableStatement().execute();
			
			resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while (resultSet.next()) {
				int staffAccountId = resultSet.getInt("sa_id");
				String username = resultSet.getString("sa_username");
				String password = resultSet.getString("sa_password");
				
				staffAccount.setId(staffAccountId);
				staffAccount.setUsername(username);
				staffAccount.setPassword(password);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return staffAccount;
	}

	@Override
	public List<Integer> getListStaffAccountPermissionByStaffAccountId(int staffAccountId) {
		List<Integer> listStaffAccountPermission = new ArrayList<Integer>();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_account_permission_getbystaffaccountid(?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_staffaccountid", staffAccountId);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			oracleConnection.getCallableStatement().execute();
			
			ResultSet resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while(resultSet.next()) {
				int staffPermissionId = resultSet.getInt("sp_id");
				
				listStaffAccountPermission.add(staffPermissionId);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return listStaffAccountPermission;
	}

	@Override
	public int insertStaffAccount(String accountUsername, int staffId, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_account_ins(?,?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setString("i_username", accountUsername);
			oracleConnection.getCallableStatement().setInt("i_staffid", staffId);
			oracleConnection.getCallableStatement().setString("i_createuser", userName);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.INTEGER);
			
			oracleConnection.getCallableStatement().executeUpdate();
		
			result = oracleConnection.getCallableStatement().getInt("o_result");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return result;
	}
	
	@Override
	public int insertStaffAccountPermission(int staffAccountId, int staffAccountPermissionId) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_account_permission_ins(?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_staffaccountid", staffAccountId);
			oracleConnection.getCallableStatement().setInt("i_staffpermissionid", staffAccountPermissionId);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.INTEGER);
			
			oracleConnection.getCallableStatement().executeUpdate();
		
			result = oracleConnection.getCallableStatement().getInt("o_result");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return result;
	}

	@Override
	public int removeStaffAccountPermission(int staffAccountId, int staffAccountPermissionId) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_account_permission_del(?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_staffaccountid", staffAccountId);
			oracleConnection.getCallableStatement().setInt("i_staffpermissionid", staffAccountPermissionId);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.INTEGER);
			
			oracleConnection.getCallableStatement().executeUpdate();
		
			result = oracleConnection.getCallableStatement().getInt("o_result");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return result;
	}

	@Override
	public int deleteStaffAccountById(int id, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_account_del(?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_id", id);
			oracleConnection.getCallableStatement().setString("i_deleteuser", userName);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.INTEGER);
			
			oracleConnection.getCallableStatement().executeUpdate();
		
			result = oracleConnection.getCallableStatement().getInt("o_result");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return result;
	}

	@Override
	public int deleteStaffAccountPermissionByStaffAccountId(int staffAccountId) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_account_permission_delbystaffaccountid(?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_staffaccountid", staffAccountId);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.INTEGER);
			
			oracleConnection.getCallableStatement().executeUpdate();
		
			result = oracleConnection.getCallableStatement().getInt("o_result");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return result;
	}

}
