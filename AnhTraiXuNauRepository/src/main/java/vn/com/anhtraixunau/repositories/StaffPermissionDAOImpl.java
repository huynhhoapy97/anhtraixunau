package vn.com.anhtraixunau.repositories;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.com.anhtraixunau.connections.OracleConnection;
import vn.com.anhtraixunau.models.StaffPermission;

public class StaffPermissionDAOImpl implements StaffPermissionDAO {
	private OracleConnection oracleConnection;
	
	@Override
	public List<StaffPermission> getListStaffPermissionExisting() {
		List<StaffPermission> listStaffPermission = new ArrayList<StaffPermission>();
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_permission_get(?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			oracleConnection.getCallableStatement().execute();
			
			ResultSet resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while(resultSet.next()) {
				int id = resultSet.getInt("sp_id");
				String name = resultSet.getString("sp_name");
				String createUser = resultSet.getString("sp_createuser");
				Date createDate = resultSet.getDate("sp_createdate");
				
				StaffPermission staffPermission = new StaffPermission();
				staffPermission.setId(id);
				staffPermission.setName(name);
				staffPermission.setCreateUser(createUser);
				staffPermission.setCreateDate(createDate);
				
				listStaffPermission.add(staffPermission);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return listStaffPermission;
	}

	@Override
	public StaffPermission getStaffPermissionById(int id) {
		StaffPermission staffPermission = new StaffPermission();
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_permission_getbyid(?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_id", id);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			oracleConnection.getCallableStatement().execute();
			
			ResultSet resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while (resultSet.next()) {
				String name = resultSet.getString("sp_name");
				String createUser = resultSet.getString("sp_createuser");
				Date createDate = resultSet.getDate("sp_createdate");
				
				staffPermission.setId(id);
				staffPermission.setName(name);
				staffPermission.setCreateUser(createUser);
				staffPermission.setCreateDate(createDate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return staffPermission;
	}

	@Override
	public int insertStaffPermission(String name, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			String query = "{call atxn_staff_permission_ins(?,?,?)}";
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setString("i_name", name);
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
	public int updateStaffPermission(int id, String name, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			String query = "{call atxn_staff_permission_upd(?,?,?,?)}";
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_id", id);
			oracleConnection.getCallableStatement().setString("i_name", name);
			oracleConnection.getCallableStatement().setString("i_updateuser", userName);
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
	public int deleteStaffPermissionById(int id, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			String query = "{call atxn_staff_permission_del(?,?,?)}";
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
}
