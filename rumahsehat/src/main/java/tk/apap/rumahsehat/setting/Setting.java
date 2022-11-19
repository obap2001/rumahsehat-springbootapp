package tk.apap.rumahsehat.setting;

public class Setting {
  final public static String CLIENT_BASE_URL = "http://localhost:8080";
  final public static String CLIENT_LOGIN = CLIENT_BASE_URL + "/validate-ticket";
  final public static String CLIENT_LOGOUT = CLIENT_BASE_URL + "/logout";
  final public static String SERVER_BASE_URL = "https://sso.ui.ac.id/cas2";
  final public static String SERVER_LOGIN = SERVER_BASE_URL + "/login?service=";
  final public static String SERVER_LOGOUT = SERVER_BASE_URL + "/logout?url=";
  final public static String SERVER_VALIDATE_TICKET
    = SERVER_BASE_URL + "/serviceValidate?ticket=%s&service=%s";
  final public static String obatUrl = "https://dbee3a3c-a0d1-49ac-81f2-4c4fdec33004.mock.pstmn.io";
}
