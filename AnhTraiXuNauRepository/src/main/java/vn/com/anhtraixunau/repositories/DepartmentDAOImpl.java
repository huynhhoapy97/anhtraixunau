package vn.com.anhtraixunau.repositories;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.com.anhtraixunau.connections.OracleConnection;
import vn.com.anhtraixunau.models.Department;
import vn.com.anhtraixunau.models.StaffPermission;

public class DepartmentDAOImpl implements DepartmentDAO {
	private OracleConnection oracleConnection;
	
	@Override
	public List<Department> getListDepartmentExisting() {
		List<Department> listDepartment = new ArrayList<Department>();
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_department_get(?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			oracleConnection.getCallableStatement().execute();
			
			ResultSet resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while(resultSet.next()) {
				int id = resultSet.getInt("d_id");
				String name = resultSet.getString("d_name");
				String createUser = resultSet.getString("d_createuser");
				Date createDate = resultSet.getDate("d_createdate");
				
				Department department = new Department();
				department.setId(id);
				department.setName(name);
				department.setCreateUser(createUser);
				department.setCreateDate(createDate);
				
				listDepartment.add(department);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return listDepartment;
	}

	@Override
	public Department getDepartmentById(int id) {
		Department department = new Department();
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_department_getbyid(?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_id", id);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			oracleConnection.getCallableStatement().execute();
			
			ResultSet resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while (resultSet.next()) {
				String name = resultSet.getString("d_name");
				String createUser = resultSet.getString("d_createuser");
				Date createDate = resultSet.getDate("d_createdate");
				
				department.setId(id);
				department.setName(name);
				department.setCreateUser(createUser);
				department.setCreateDate(createDate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return department;
	}

	@Override
	public List<Integer> getListDepartmentPermissionByDepartmentId(int departmentId) {
		List<Integer> listDepartmentPermission = new ArrayList<Integer>();
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_department_permission_getbydepartmentid(?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_departmentid", departmentId);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			oracleConnection.getCallableStatement().execute();
			
			ResultSet resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while(resultSet.next()) {
				int staffPermissionId = resultSet.getInt("sp_id");
				
				listDepartmentPermission.add(staffPermissionId);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return listDepartmentPermission;
	}

	@Override
	public int insertDepartment(String name, String userName) {
		int result = -1;
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_department_ins(?,?,?)}";
			
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
	public int updateDepartment(int id, String name, String userName) {
		int result = -1;
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_department_upd(?,?,?,?)}";
			
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
	public int insertDepartmentPermission(int departmentId, int permissionId) {
		int result = -1;
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_department_permission_ins(?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_departmenid", departmentId);
			oracleConnection.getCallableStatement().setInt("i_staffpermissionid", permissionId);
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
	public int removeDepartmentPermission(int departmentId, int permissionId) {
		int result = -1;
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_department_permission_del(?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_departmentid", departmentId);
			oracleConnection.getCallableStatement().setInt("i_staffpermissionid", permissionId);
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
	public int deleteDepartmentById(int id, String userName) {
		int result = -1;
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_department_del(?,?,?)}";
			
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
	public int deleteDepartmentPermissionByDepartmentId(int departmentId) {
		int result = -1;
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_department_permission_delbydepartmentid(?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_departmentid", departmentId);
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
	public List<StaffPermission> getListStaffPermissionByDepartmentId(int departmentId) {
		List<StaffPermission> listStaffPermission = new ArrayList<StaffPermission>();
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_department_permission_getbydepartmentid(?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_departmentid", departmentId);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			oracleConnection.getCallableStatement().execute();
			
			ResultSet resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while(resultSet.next()) {
				int staffPermissionId = resultSet.getInt("sp_id");
				String staffPermissionName = resultSet.getString("sp_name");
				
				StaffPermission staffPermission = new StaffPermission();
				staffPermission.setId(staffPermissionId);
				staffPermission.setName(staffPermissionName);
				
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
}
