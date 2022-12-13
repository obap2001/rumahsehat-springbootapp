package tk.apap.rumahsehat.setting;

public class Setting {
  final private static String CLIENT_BASE_URL = "http://apap-078.cs.ui.ac.id";
  final private static String CLIENT_LOGIN = CLIENT_BASE_URL + "/validate-ticket";
  final private static String CLIENT_LOGOUT = CLIENT_BASE_URL + "/logout";
  final private static String SERVER_BASE_URL = "https://sso.ui.ac.id/cas2";
  final private static String SERVER_LOGIN = SERVER_BASE_URL + "/login?service=";
  final private static String SERVER_LOGOUT = SERVER_BASE_URL + "/logout?url=";
  final private static String SERVER_VALIDATE_TICKET
    = SERVER_BASE_URL + "/serviceValidate?ticket=%s&service=%s";
}
