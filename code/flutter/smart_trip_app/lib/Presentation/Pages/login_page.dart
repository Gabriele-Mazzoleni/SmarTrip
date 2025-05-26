import 'package:flutter/material.dart';
import 'package:smart_trip_app/Presentation/Controllers/login_page_controller.dart';
import 'package:smart_trip_app/Presentation/Pages/map_selection_page.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';


class LoginPage extends StatefulWidget {
  final String ip;
  const LoginPage({super.key, required this.ip});

  @override
  // ignore: library_private_types_in_public_api
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {

  final TextEditingController _userNameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  final TextEditingController _signInUsernameController = TextEditingController();
  final TextEditingController _signInPasswordController = TextEditingController();

  String errorMessage = '';
  String signInErrorMessage = '';
  bool isLoading = false;
  bool isSignInLoading = false;


   void navigateToMapSelectionPage(user, ip) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => MapSelectionPage(user: user, ip:ip),
      ),
    );
  }


Future<void> login() async {
    setState(() {
      isLoading = true;
      errorMessage = '';
    });

    try {
      final user = await userSearcher(
        _userNameController.text,
        _passwordController.text,
        widget.ip,
      );
      navigateToMapSelectionPage(user,widget.ip); 
    } catch (e) {
      setState(() {
        errorMessage = 'Invalid credentials. Please try again.';
        isLoading = false;
        _userNameController.clear();
        _passwordController.clear();
      });
    }
  }


  Future<void> signin(StateSetter setState) async {
    try {
      final user = await userAdder(
        _signInUsernameController.text,
        _signInPasswordController.text,
        widget.ip,
      );
      Navigator.of(context).pop();
      navigateToMapSelectionPage(user,widget.ip);
    } catch (e) {
      setState(() {
        signInErrorMessage = 'Error during sign-in. Please try again.';
        isSignInLoading = false;
        _signInUsernameController.clear();
        _signInPasswordController.clear();
      });
    }
  }
  
  
   void _showSignInModal(BuildContext context) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return StatefulBuilder(
          builder: (context, setState) {
            return AlertDialog(
              content: SingleChildScrollView(
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    TextField(
                      style: FontStyles.signinText,
                      controller: _signInUsernameController,
                      decoration: const InputDecoration(
                        hintText: 'Username',
                        hintStyle: FontStyles.signinText,
                        focusedBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: AppColors.black),
                        ),
                      ),
                    ),
                    const SizedBox(height: Sizes.smallPaddingSpace),
                    TextField(
                      style: FontStyles.signinText,
                      controller: _signInPasswordController,
                      decoration: const InputDecoration(
                        hintText: 'Password',
                        hintStyle: FontStyles.signinText,
                        focusedBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: AppColors.black),
                        ),
                      ),
                      obscureText: true,
                    ),
                    if (signInErrorMessage.isNotEmpty)
                      Padding(
                        padding: const EdgeInsets.only(top: 8.0),
                        child: Text(
                          signInErrorMessage,
                          style: const TextStyle(color: Colors.red),
                        ),
                      ),
                  ],
                ),
              ),
              actions: [
                TextButton(
                  style: ElevatedButton.styleFrom(
                        backgroundColor: AppColors.red,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                        ),
                      ),
                  onPressed: () {
                    signInErrorMessage='';
                    Navigator.of(context).pop();
                  },
                  child: const Text('ANNULLA', style:FontStyles.buttonTextWhite),
                ),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                        backgroundColor: AppColors.red,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                        ),
                      ),
                  onPressed: isSignInLoading ? null : () {
                    setState(() {
                      isSignInLoading = true;
                      signInErrorMessage = '';
                    });
                    signin(setState);
                  },
                  child: isSignInLoading
                      ? const CircularProgressIndicator(color: AppColors.black)
                      : const Text('CONFERMA', style: FontStyles.buttonTextWhite),
                ),
              ],
            );
          },
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: AppColors.gray,
      body: SingleChildScrollView(
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 24.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const SizedBox(height: Sizes.stdPaddingSpace), 
                  Image.asset('assets/appLogo_red_black.png', height: Sizes.logoSize,),
                  const SizedBox(height: Sizes.smallPaddingSpace),
                  const Text(
                    'Benvenuto in',
                    style: FontStyles.loginTitle,
                  ),
                  const Text(
                    'SmarTrip',
                    style: FontStyles.loginTitleRed,
                  ),
                  const SizedBox(height: Sizes.smallPaddingSpace),
                  TextField(
                    style: FontStyles.loginText,
                    controller: _userNameController,
                    decoration: const InputDecoration(
                      labelText: 'Username',
                      labelStyle: FontStyles.loginText,
                      enabledBorder: UnderlineInputBorder(
                        borderSide: BorderSide(color: Colors.black),
                      ),
                      focusedBorder: UnderlineInputBorder(
                        borderSide: BorderSide(color: AppColors.black),
                      ),
                    ),
                  ),
                  const SizedBox(height: Sizes.smallPaddingSpace),
                  TextField(
                    style: FontStyles.loginText,
                    controller: _passwordController,
                    obscureText: true,
                    decoration: const InputDecoration(
                      labelText: 'Password',
                      labelStyle: FontStyles.loginText,
                      enabledBorder: UnderlineInputBorder(
                        borderSide: BorderSide(color: AppColors.black),
                      ),
                      focusedBorder: UnderlineInputBorder(
                        borderSide: BorderSide(color: AppColors.black),
                      ),
                    ),
                  ),
                  const SizedBox(height: 20),
                  ElevatedButton(
                    onPressed: () {
                      login();
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: AppColors.red,
                      minimumSize: const Size(double.infinity, 50), // Larghezza bottone
                      shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                            ),
                    ),
                    child: isLoading
                      ? const CircularProgressIndicator(color: AppColors.white)
                      : const Text(
                        'ACCEDI',
                        style: FontStyles.buttonTextWhite,
                        ),
                  ),
                  if (errorMessage.isNotEmpty)
                          Padding(
                            padding: const EdgeInsets.only(top: Sizes.smallPaddingSpace),
                            child: Text(
                              errorMessage,
                              style: const TextStyle(color: AppColors.red),
                            ),
                          ),
                  const SizedBox(height:Sizes.stdPaddingSpace),
                  const Text(
                    'Non hai un account?',
                    style: FontStyles.subTitle,
                  ),
                  const SizedBox(height: 10),
                  ElevatedButton(
                    onPressed: () {
                      _showSignInModal(context);
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor:AppColors.white,
                      minimumSize: const Size(double.infinity, 50), // Larghezza bottone
                      shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                            ),
                    ),
                    child: const Text(
                      'REGISTRATI',
                      style: FontStyles.buttonTextRed,
                    ),
                  ),
                  const SizedBox(height:Sizes.smallPaddingSpace),
                ],
              ),
            ),
            Align(
                    alignment: Alignment.bottomCenter,
                    child: Image.asset(
                      'assets/login_graphic.png',
                      fit: BoxFit.cover,
                    ),
                  )
          ],
        ),
      ),
    );
  }
}