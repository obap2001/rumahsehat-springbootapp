class LoginResponse {
  final String token;
  final String message;
  final bool error;

  LoginResponse({required this.token, required this.message, required this.error});

  factory LoginResponse.fromJson(Map<String, dynamic> data) {
    // note the explicit cast to String
    // this is required if robust lint rules are enabled
    final token = data['token'] as String;
    final message = data['message'] as String;
    final error = data['error'] as bool;

    return LoginResponse(token: token, message: message, error: error);
  }

}
