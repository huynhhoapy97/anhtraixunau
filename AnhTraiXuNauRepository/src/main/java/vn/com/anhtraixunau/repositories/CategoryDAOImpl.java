package vn.com.anhtraixunau.repositories;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import vn.com.anhtraixunau.connections.OracleConnection;
import vn.com.anhtraixunau.models.Category;

public class CategoryDAOImpl implements CategoryDAO {
	private OracleConnection oracleConnection;
	private ResultSet resultSet;
	private String query;
	
	@Override
	public int insertCategory(String categoryName, String categoryDescription, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_category_ins(?,?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setString("i_name", categoryName);
			oracleConnection.getCallableStatement().setString("i_description", categoryDescription);
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
	public int updateCategory(int categoryId, String categoryName, String categoryDescription, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_category_upd(?,?,?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_id", categoryId);
			oracleConnection.getCallableStatement().setString("i_name", categoryName);
			oracleConnection.getCallableStatement().setString("i_description", categoryDescription);
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
	public int deleteCategory(int id, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_category_del(?,?,?)}";
			
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
	public List<Category> getListCategoryInformation() {
		List<Category> listCategoryInformation = new ArrayList<Category>();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_category_get(?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			
			oracleConnection.getCallableStatement().execute();
			
			resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while (resultSet.next()) {
				int categoryId = resultSet.getInt("c_id");
				String categoryName = resultSet.getString("c_name");
				String categoryDescription = resultSet.getString("c_description");
				Date createDate = resultSet.getDate("c_createdate");
				String createUser = resultSet.getString("c_createuser");
				
				Category category = new Category();
				category.setId(categoryId);
				category.setName(categoryName);
				category.setDescription(categoryDescription);
				category.setCreateDate(createDate);
				category.setCreateUser(createUser);
				
				listCategoryInformation.add(category);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return listCategoryInformation;
	}

	@Override
	public Category getCategoryById(int id) {
		Category category = new Category();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_category_getbyid(?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_id", id);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			oracleConnection.getCallableStatement().execute();
			
			ResultSet resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while (resultSet.next()) {
				String name = resultSet.getString("c_name");
				String description = resultSet.getString("c_description");
				
				category.setId(id);
				category.setName(name);
				category.setDescription(description);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return category;
	}
}
