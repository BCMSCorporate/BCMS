package com.wise.bcms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wise.bcms.dao.CompanyDAO;
import com.wise.bcms.dao.CouponReqDAO;
import com.wise.bcms.dto.Company;
import com.wise.bcms.dto.CouponReq;
import com.wise.bcms.dto.Customer;
import com.wise.bcms.util.DBUtility;
/**
 * Servlet implementation class FrontController
 */
@WebServlet("./BCMSController")
public class BCMSController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD_SIZE=1024*1024*3;
	private static final int MAX_FILE_SIZE=1024*1024*40;
	private static final int MAX_REQUEST_SIZE=1024*1024*50;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BCMSController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println(action);
		if (action.equals("index")) {
			response.sendRedirect("index.jsp");
			return;
		}

		if (action.equals("company")) {
			response.sendRedirect("companyLogin.jsp");
			return;
		}
		if (action.equals("home")) {
			response.sendRedirect("companyPanel.jsp");
			return;
		}
		
		if (action.equals("profile")) {
			viewCompanyProfile(request).forward(request, response);
		}

		if (action.equals("viewFile")) {
			viewCustomerCoupons(request).forward(request, response);
		}
		if (action.equals("Signup")) {
			response.sendRedirect("companySignup.jsp");
			return;
		}		
		
		if (action.equals("customer")) {
			response.sendRedirect("customerLogin.jsp");
			return;
		}
		if (action.equals("uploadFile")) {
			response.sendRedirect("uploadFile.jsp");
			return;
		}
		if (action.equals("signOut") || action.equals(null)) {
			response.sendRedirect("companyLogin.jsp");
			return;
		}		
		
	}

	private RequestDispatcher viewCustomerCoupons(HttpServletRequest request) {
        List<CouponReq> couponreqlist;
        RequestDispatcher dispatcher=null;
        CouponReqDAO couponreq = new CouponReqDAO();
        couponreqlist =  new ArrayList<CouponReq>(couponreq.get());
        request.setAttribute("couponreqlist", couponreqlist);
        dispatcher = request.getRequestDispatcher("viewCouponReqs.jsp");   
        return dispatcher;
	}

	private RequestDispatcher viewCompanyProfile(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		Company company = (Company) session.getAttribute("cuser");
		request.setAttribute("ccompany", company);
		RequestDispatcher rd = request.getRequestDispatcher("companyProfile.jsp");
		return rd;
	}

	private RequestDispatcher SignUpAsCompany(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String doe = request.getParameter("doe");
		String dor = request.getParameter("dor");
		String pwd = request.getParameter("password");
		String emailId = request.getParameter("email_id");
		String survey_no = request.getParameter("survey_no");
		String landmark = request.getParameter("landmark");
		String location = request.getParameter("location");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String pincode = request.getParameter("pincode");
		CompanyDAO dao = new CompanyDAO();
		Company company = new Company();
		
		company.setId(Integer.parseInt(id));
		company.setName(name);
		company.setDoe(DBUtility.StringToUtil(doe));
		company.setDor(DBUtility.StringToUtil(dor));
		company.setPassword(pwd);
		company.setEmail(emailId);
		company.setLandmark(landmark);
		company.setLocation(location);
		company.setSurveyNo(Integer.parseInt(survey_no));
		company.setCity(city);
		company.setState(state);
		company.setCountry(country);
		company.setPincode(Integer.parseInt(pincode));
		int status = dao.add(company);
		if(status>=0){
			request.setAttribute("signUpStatus:","SignUp Successfull.!");
		}
		else{
			request.setAttribute("signUpStatus:","SignUp Failed.!");	
		}
		RequestDispatcher rd = request.getRequestDispatcher("companySignup.jsp");
		return rd;
	}
	
	private RequestDispatcher loginAsCompany(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher=null;
		String id = request.getParameter("companyId");
		String pwd = request.getParameter("password");
		CompanyDAO dao = new CompanyDAO();
		Company company = dao.get(Integer.parseInt(id));
		if(company.getPassword().equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("cuser", company);
			dispatcher = request.getRequestDispatcher("companyPanel.jsp");	
		}else {			
			dispatcher = request.getRequestDispatcher("companyLogin.jsp");
		}		 
		return dispatcher;
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);	

		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String uploadFolder = "";
		if (isMultipart) {
		   DiskFileItemFactory factory = new DiskFileItemFactory();
		   factory.setSizeThreshold(THRESHOLD_SIZE);
		   factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		   
		   ServletFileUpload upload = new ServletFileUpload(factory);
		   upload.setFileSizeMax(MAX_FILE_SIZE);
		   upload.setSizeMax(MAX_REQUEST_SIZE);

		   try {
		    	List<FileItem> file = upload.parseRequest(request);
		    	String actionn = getFieldValue(file, "action");
		    	if (actionn.equals("fileStatus")) {
		    		uploadFile(request, response, file).forward(request, response);
		    		return;
		    	}
		    			    	
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } 
		}

		

		
		String action = request.getParameter("action");
		System.out.println(action);
		if (action.equals("CompanyLogin")) {
			loginAsCompany(request).forward(request, response);
			return;
		}
		if (action.equals("CompanySignUp")) {
			SignUpAsCompany(request).forward(request, response);
			return;
		}
	}

	private RequestDispatcher uploadFile(HttpServletRequest request, HttpServletResponse response,
			List<FileItem> file) throws IOException {
		final String UPLOAD_DIRECTORY="Files";
		final String path = "fileUpload";
		String uploadPath = getUploadPath(request, UPLOAD_DIRECTORY, path, file);
		HttpSession httpSession = request.getSession(false);
		Company company = (Company) httpSession.getAttribute("cuser");
		CouponReqDAO dao = new CouponReqDAO();
		if(add(uploadPath, company.getId()) != 0) {
			request.setAttribute("msg", "Upload has done successfully...!");
			
		}
		
		return request.getRequestDispatcher("uploadFile.jsp");
	}

	private int add(String uploadPath, int id) throws IOException {
		// TODO Auto-generated method stub

		System.out.println(uploadPath);
		FileInputStream fis = new FileInputStream(new File(uploadPath));
		 Workbook wb = new HSSFWorkbook(fis);
		 Sheet sheet = wb.getSheetAt(0);
		 Iterator<Row> iterator = sheet.iterator();
		 int status = 0;
		 String months[] = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		 while (iterator.hasNext()) {
			 Row nextRow = iterator.next();
			 Iterator<Cell> cellIterator = nextRow.cellIterator();
			 CouponReq couponReq = new CouponReq();
			 while (cellIterator.hasNext()) {
				 Cell nextCell = cellIterator.next();
				 int columnIndex = nextCell.getColumnIndex();
				 switch(columnIndex) {
				 case 0:
					 Customer customer=new Customer();
					 customer.setId(Integer.parseInt(getCellValue(nextCell)));
					 couponReq.setCustomer(customer);
					 break;
				 case 1: couponReq.setAmount(Integer.parseInt(getCellValue(nextCell)));
				 	     break;
				 case 2: couponReq.setMonth(months[Integer.parseInt(getCellValue(nextCell)) -1]);
				 		break;
				 case 3: couponReq.setYear(Integer.parseInt(getCellValue(nextCell)));
				 		break;
				 }
				 }
				CouponReqDAO dao = new CouponReqDAO();
				status = dao.add(couponReq);
		
		 }
		 
		 return status;
	}

	private String getCellValue(Cell nextCell) {
		// TODO Auto-generated method stub
		CellType type = nextCell.getCellTypeEnum();
		if (type == CellType.STRING)
			return nextCell.getRichStringCellValue().toString();
		else if (type == CellType.NUMERIC)
			return "" + ((int)Math.ceil(nextCell.getNumericCellValue()));
		return "BLANK";
	}

	private String getUploadPath(HttpServletRequest request, String UPLOAD_DIRECTORY, String path,
			List<FileItem> file) {
		// TODO Auto-generated method stub
		String fileName = null; 
		String filePath = null;
		String uploadPath = getServletContext().getInitParameter(path) + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			Iterator iterator = file.iterator();
			while(iterator.hasNext()) {
				FileItem item = (FileItem) iterator.next();
				if(!item.isFormField()) {
					fileName = new File(item.getName()).getName();
						HttpSession session = request.getSession(false);
						Company company = (Company) session.getAttribute("cuser");
						int company_id = company.getId();
						long todayDate = System.currentTimeMillis();
						fileName = company_id + "_" + todayDate + "_" + fileName;
						filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						item.write(storeFile);
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}

	private String getFieldValue(List<FileItem> file, String fieldName) {
		// TODO Auto-generated method stub
		String value = null;
		for (FileItem f : file) {
			if(f.getFieldName().equals(fieldName)) {
				value = f.getString();
			}
		}
		return value;
	}
}
