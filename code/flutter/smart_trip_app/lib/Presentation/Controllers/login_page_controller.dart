import 'package:smart_trip_app/Domain/user.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

Future<User> userSearcher(String username, String password, String indirizzo) async {
  var url = Uri.parse('uri dell API');

  final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(<String, Object>{
      'password': password,
    }),
  );

  //print('Response status: ${response.statusCode}');
  //print('Response body: ${response.body}');  
  
  if (response.statusCode == 200) {
    var data = json.decode(response.body);
    if (data is Map<String, dynamic>) {
      var user=User.fromJSON(data);
      return user;
    } else {
      throw Exception('Unexpected response format');
    }
  } else {
    throw Exception('User not found');
  }
}

Future<User> userAdder(String username, String password, String indirizzo) async {
  var url = Uri.parse('https://i0cnfc4p7d.execute-api.us-east-1.amazonaws.com/default/SignIn');

   final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(<String, Object>{
      
      'username': username,
      'password': password,
    }),
  );

  //print('Response status: ${response.statusCode}');
  //print('Response body: ${response.body}');  

  if (response.statusCode >= 200 && response.statusCode<300) {
    var user= User(
      username: username,
      password: password,
      status: 'Success'
    );
    return user;
    
  } else {
    throw Exception('Invalid credentials');
  }
}