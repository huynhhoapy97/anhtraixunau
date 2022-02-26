package vn.com.anhtraixunau.repositories;

import java.sql.ResultSet;
import java.sql.Types;

import vn.com.anhtraixunau.connections.OracleConnection;
import vn.com.anhtraixunau.enums.AdminAccountMessage;
import vn.com.anhtraixunau.models.AdminAccount;

public class AdminAccountDAOImpl implements AdminAccountDAO {
	private OracleConnection oracleConnection;
	
	@Override
	public int checkAccountExists(String username, String password) {
		int result = AdminAccountMessage.MESSAGE_ERROR_EXISTS.getId();
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_account_chk(?,?)}";
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setString("i_username", username);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.INTEGER);
			
			oracleConnection.getCallableStatement().execute();
			result = oracleConnection.getCallableStatement().getInt("o_result");
		}
		catch (Exception e) {
			e.printStackTrace();
			
			result = AdminAccountMessage.MESSAGE_ERROR_CHECKACCOUNTEXISTS.getId();
		}
		finally {
			oracleConnection.close();
		}
		
		return result;
	}

	@Override
	public int changePassword(String username, String password) {
		int result = AdminAccountMessage.MESSAGE_NO_ERROR.getId();
		String query = new String();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_staff_account_upd(?,?,?)}";
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setString("i_username", username);
			oracleConnection.getCallableStatement().setString("i_password", password);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.INTEGER);
			
			oracleConnection.getCallableStatement().executeUpdate();
			
			result = oracleConnection.getCallableStatement().getInt("o_result");
		}
		catch (Exception e) {
			e.printStackTrace();
			
			result = AdminAccountMessage.MESSAGE_ERROR_CHANGEPASSWORD.getId();
		}
		finally {
			oracleConnection.close();
		}

		return result;
	}

	@Override
	public AdminAccount getInformationByUsername(String username) {
		oracleConnection = new OracleConnection();
		AdminAccount adminAccount = new AdminAccount();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			String query = "{call atxn_staff_account_get(?,?)}";
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setString("i_username", username);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			
			oracleConnection.getCallableStatement().execute();
			
			ResultSet resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while (resultSet.next()) {
				int id = resultSet.getInt("sa_id");
				String password = resultSet.getString("sa_password");
				
				adminAccount.setId(id);
				adminAccount.setPassword(password);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return adminAccount;
	}
}
