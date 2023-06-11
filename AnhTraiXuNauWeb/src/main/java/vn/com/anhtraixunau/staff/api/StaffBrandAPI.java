package vn.com.anhtraixunau.staff.api;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.com.anhtraixunau.enums.BrandMessage;
import vn.com.anhtraixunau.models.Brand;
import vn.com.anhtraixunau.services.BrandService;


@RestController
@RequestMapping("staff/api/brand")
public class StaffBrandAPI {
    private BrandService brandService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping("getListBrandInformation")
    public Map<String, Object> getListBrandInformation() {
        Map<String, Object> response = new HashMap<String, Object>();
        List<Brand> listBrandInformation = new ArrayList<Brand>();

        try {
            brandService = new BrandService();
            listBrandInformation = brandService.getListBrandInformation();

            response.put("listBrandInformation", listBrandInformation);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping("getBrandById/{id}")
    public Map<String, String> getBrandById(@PathVariable("id") Integer id) {
        Map<String, String> response = new HashMap<String, String>();
        Brand brand = new Brand();
        brandService = new BrandService();

        try {
            brand = brandService.getBrandById(id);

            if (brand.getId() != 0) {
                response.put("brandId", String.valueOf(brand.getId()));
                response.put("brandName", brand.getName());
                response.put("brandDescription", brand.getDescription());
            } else {
                response.put("message", "Không có thông tin thương hiệu");
            }
        } catch (Exception e) {
            e.printStackTrace();

            response.put("message", "Lỗi lấy thông tin thương hiệu");
        }

        return response;
    }

    @PostMapping("completeBrandEdit")
    public Map<String, Object> completeBrandEdit(@RequestBody Brand brand, HttpServletRequest httpServletRequest) {
        Map<String, Object> response = new HashMap<String, Object>();
        List<Brand> listBrandInformation = new ArrayList<Brand>();
        brandService = new BrandService();

        int brandId = -1;
        String brandName = "";
        String brandDescription = "";
        String userName = "";

        try {
            brandId = brand.getId();
            brandName = brand.getName();
            brandDescription = brand.getDescription();
            userName = (String) httpServletRequest.getSession().getAttribute("username");

            /*if (brandId == BrandMessage.DOES_NOT_EXISTS_BRANDID.getId()) {
                brandService.insertBrand(brandName, brandDescription, userName);
            } else {
                brandService.updateBrand(brandId, brandName, brandDescription, userName);
            }*/

            listBrandInformation = brandService.getListBrandInformation();

            response.put("listBrandInformation", listBrandInformation);
        } catch (Exception e) {
            e.printStackTrace();

            response.put("message", "Lỗi thêm/sửa thông tin thương hiệu");
        }

        return response;
    }

    @PostMapping("deleteBrandById/{id}")
    public Map<String, Object> deleteBrandById(@PathVariable("id") Integer id, HttpServletRequest httpServletRequest) {
        Map<String, Object> response = new HashMap<String, Object>();
        List<Brand> listBrandInformation = new ArrayList<Brand>();
        String userName = "";
        brandService = new BrandService();

        try {
            userName = (String) httpServletRequest.getSession().getAttribute("username");

            brandService.deleteBrandById(id, userName);

            listBrandInformation = brandService.getListBrandInformation();

            response.put("listBrandInformation", listBrandInformation);
        } catch (Exception e) {
            e.printStackTrace();

            response.put("message", "Lỗi xóa thông tin thương hiệu");
        }

        return response;
    }

    @PostMapping("imageUpload")
    @ResponseBody
    public String imageUpload(@RequestParam("upload") MultipartFile fileUpload, @RequestParam("CKEditorFuncNum") String callback,
                              HttpServletRequest httpServletRequest) {

        // Lấy tên của ảnh tải lên
        String fileName = fileUpload.getOriginalFilename();
        // Lấy tên đuôi ảnh tải lên jpg hoặc png
        String fileExtension = FilenameUtils.getExtension(fileName);
        // Đường dẫn lưu ảnh trên Server
        String path = "";
        String pathDestination = "";
        // URL ảnh trả về Client
        String imageURL = "";
        // Nội dung trả về Client sau khi gửi ảnh đến Server
        String response = "";

        try {
            if (fileExtension != null && fileExtension.trim() != "") {
                if (fileExtension.trim().equalsIgnoreCase("jpg") || fileExtension.trim().equalsIgnoreCase("png")) {
                    // Vị trí lưu ảnh trên Server được upload lên từ Client
                    path = "/resources/images/upload/" + fileName;
                    pathDestination = servletContext.getRealPath(path);
                    File fileDestination = new File(pathDestination);

                    // Đổ dữ liệu file ảnh từ Client vào dữ liệu file ảnh trên Server
                    fileUpload.transferTo(fileDestination);

                    String scheme = httpServletRequest.getScheme();
                    String serverName = httpServletRequest.getServerName();
                    String serverPort = String.valueOf(httpServletRequest.getServerPort());

                    imageURL = scheme.concat("://").concat(serverName).concat(":").concat(serverPort).concat(path);

                    // Upload file ảnh này ngược lại URL trên CKEditor
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("<script type='text/javascript'>function delay(n) {return new Promise(done => { setTimeout(() => {");
                    stringBuffer.append("window.parent.CKEDITOR.tools.callFunction(");
                    stringBuffer.append(callback);
                    stringBuffer.append(",'");
                    stringBuffer.append(imageURL);
                    stringBuffer.append("','Success');");
                    stringBuffer.append("}, n);});}");
                    stringBuffer.append("delay(10000);");
                    stringBuffer.append("</script>");

                    response = stringBuffer.toString();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }
}
