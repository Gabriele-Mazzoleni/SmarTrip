class User{
  final String username;
  final String password;

 User({
    required this.username,
    required this.password,
  });

  User.fromJSON(Map<String, dynamic> jsonMap) :
    username = (jsonMap['Username'] ?? ""),
    password = (jsonMap['Password'] ?? "");


}