package huulam.util;

import huulam.model.EmployeeViewModel;

public class Utility {
	
	private static final String FULLNAME_PATTERN =
            "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ" +
            "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ" +
            "ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$";
	
	public boolean isNameValid(String name) {
		if (name.equals("")) return false;
		if (name.length() > 100 || name.length() < 6) return false;
		if(!verifyFullname(name)) return false;
		if(!firstUppercaseValid(name)) return false;
		return true;
	}
	
	// only accept letter and space
//	public boolean lettersValid(String str) {
//	    String regx = "^[a-zA-Z\\s]+$";
//	    Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
//	    Matcher matcher = pattern.matcher(str);
//	    return matcher.find();
//	 }
	
	// validate vietnamese fullname
	public static boolean verifyFullname(String fullname) {
	    if (fullname == null) return false;
	    return fullname.matches(FULLNAME_PATTERN);
	}
	
	// check first case is Upper or not
	public boolean firstUppercaseValid (String name) {
		String str = name.trim();
		if(!Character.isUpperCase(str.charAt(0))) return false;
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == ' ' && !Character.isUpperCase(str.charAt(i+1))) return false;
		}
		return true;
	}

	public boolean isAgeValid(int age) {
		if (age < 18) return false;
		return true;
	}
	
	public boolean isAddressValid(String address) {
		if (address.equals("")) return false;
		if (address.length() > 255 || address.length() < 6) return false;
		return true;
	}
	
	public boolean isRoleValid(String role) {
		if(role.equals("")) return false;
		if (role.length() > 50 || role.length() < 3) return false;
		return true;
	}
	
	public boolean isFormValid(EmployeeViewModel employeeVm) {
		return isNameValid(employeeVm.getName()) && isAgeValid(employeeVm.getAge())
				&& isAddressValid(employeeVm.getAddress()) && isRoleValid(employeeVm.getRole());
	}
}
