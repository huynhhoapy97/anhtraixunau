package vn.com.anhtraixunau.repositories;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import vn.com.anhtraixunau.connections.OracleConnection;
import vn.com.anhtraixunau.models.Department;
import vn.com.anhtraixunau.models.Staff;
import vn.com.anhtraixunau.models.StaffAccount;
import vn.com.anhtraixunau.models.StaffAccountTokenVerify;

public class StaffDAOImpl implements StaffDAO {
	private OracleConnection oracleConnection;
	private ResultSet resultSet;
	private String query;
	
	@Override
	public List<Staff> getListStaffInformation() {
		List<Staff> listStaffInformation = new ArrayList<Staff>();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_get(?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			
			oracleConnection.getCallableStatement().execute();
			
			resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while (resultSet.next()) {
				int staffId = resultSet.getInt("s_id");
				String firstName = resultSet.getString("s_firstname");
				String lastName = resultSet.getString("s_lastname");
				Date dateStart = resultSet.getDate("s_datestart");
				int departmentId = resultSet.getInt("d_id");
				String departmentName = resultSet.getString("d_name");
				int staffAccoutnId = resultSet.getInt("sa_id");
				String staffAccountUsername = resultSet.getString("sa_username");
				String staffAccountPassword = resultSet.getString("sa_password");
				String staffAccountEmail = resultSet.getString("sa_email");
				int staffAccountTokenId = resultSet.getInt("sat_id");
				String staffAccountToken = resultSet.getString("sat_token");
				
				Department department = new Department();
				department.setId(departmentId);
				department.setName(departmentName);
				
				StaffAccountTokenVerify staffAccountTokenVerify = new StaffAccountTokenVerify();
				staffAccountTokenVerify.setId(staffAccountTokenId);
				staffAccountTokenVerify.setToken(staffAccountToken);
				
				StaffAccount staffAccount = new StaffAccount();
				staffAccount.setId(staffAccoutnId);
				staffAccount.setUsername(staffAccountUsername);
				staffAccount.setPassword(staffAccountPassword);
				staffAccount.setEmail(staffAccountEmail);
				staffAccount.setStaffAccountTokenVerify(staffAccountTokenVerify);
				
				Staff staff = new Staff();
				staff.setId(staffId);
				staff.setFirstName(firstName);
				staff.setLastName(lastName);
				staff.setDateStart(dateStart);
				staff.setDepartment(department);
				staff.setStaffAccount(staffAccount);
				
				listStaffInformation.add(staff);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return listStaffInformation;
	}

	@Override
	public int insertStaff(String lastName, String firstName, int departmentId, Date dateStart, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_ins(?,?,?,?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_departmentid", departmentId);
			oracleConnection.getCallableStatement().setString("i_lastname", lastName);
			oracleConnection.getCallableStatement().setString("i_firstname", firstName);
			oracleConnection.getCallableStatement().setDate("i_datestart", dateStart);
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
	public int updateStaff(int staffId, String lastName, String firstName, int departmentId, Date dateStart, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_upd(?,?,?,?,?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_staffid", staffId);
			oracleConnection.getCallableStatement().setString("i_lastname", lastName);
			oracleConnection.getCallableStatement().setString("i_firstname", firstName);
			oracleConnection.getCallableStatement().setInt("i_departmentid", departmentId);
			oracleConnection.getCallableStatement().setDate("i_datestart", dateStart);
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
	public int deleteStaff(int id, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_del(?,?,?)}";
			
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
