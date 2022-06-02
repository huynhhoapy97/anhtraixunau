package vn.com.anhtraixunau.repositories;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import vn.com.anhtraixunau.connections.OracleConnection;
import vn.com.anhtraixunau.models.Brand;

public class BrandDAOImpl implements BrandDAO {
	private OracleConnection oracleConnection;
	private ResultSet resultSet;
	private String query;
	
	@Override
	public int insertBrand(String brandName, String brandDescription, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_brand_ins(?,?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setString("i_name", brandName);
			oracleConnection.getCallableStatement().setString("i_description", brandDescription);
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
	public int updateBrand(int brandId, String brandName, String brandDescription, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_brand_upd(?,?,?,?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_id", brandId);
			oracleConnection.getCallableStatement().setString("i_name", brandName);
			oracleConnection.getCallableStatement().setString("i_description", brandDescription);
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
	public int deleteBrand(int id, String userName) {
		int result = -1;
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_brand_del(?,?,?)}";
			
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
	public List<Brand> getListBrandInformation() {
		List<Brand> listBrandInformation = new ArrayList<Brand>();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_brand_get(?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			
			oracleConnection.getCallableStatement().execute();
			
			resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while (resultSet.next()) {
				int brandId = resultSet.getInt("c_id");
				String brandName = resultSet.getString("c_name");
				String brandDescription = resultSet.getString("c_description");
				Date createDate = resultSet.getDate("c_createdate");
				String createUser = resultSet.getString("c_createuser");
				
				Brand brand = new Brand();
				brand.setId(brandId);
				brand.setName(brandName);
				brand.setDescription(brandDescription);
				brand.setCreateDate(createDate);
				brand.setCreateUser(createUser);
				
				listBrandInformation.add(brand);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return listBrandInformation;
	}

	@Override
	public Brand getBrandById(int id) {
		Brand brand = new Brand();
		oracleConnection = new OracleConnection();
		
		try {
			oracleConnection.setConnection(oracleConnection.connectToOracle());
			
			query = "{call atxn_brand_getbyid(?,?)}";
			
			oracleConnection.setCallableStatement(oracleConnection.getConnection().prepareCall(query));
			
			oracleConnection.getCallableStatement().setInt("i_id", id);
			oracleConnection.getCallableStatement().registerOutParameter("o_result", Types.REF_CURSOR);
			oracleConnection.getCallableStatement().execute();
			
			ResultSet resultSet = (ResultSet) oracleConnection.getCallableStatement().getObject("o_result");
			while (resultSet.next()) {
				String name = resultSet.getString("c_name");
				String description = resultSet.getString("c_description");
				
				brand.setId(id);
				brand.setName(name);
				brand.setDescription(description);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			oracleConnection.close();
		}
		
		return brand;
	}
}
