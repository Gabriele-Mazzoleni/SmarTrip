import 'package:flutter/material.dart';
import 'package:smart_trip_app/Presentation/Pages/login_page.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';

class SplashPage extends StatefulWidget {
  const SplashPage({super.key});


  @override
  // ignore: library_private_types_in_public_api
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  @override
  void initState() {
    super.initState();
  }

  final TextEditingController _idController = TextEditingController();

  void navigateToLoginPage(ip){
  Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => LoginPage(ip: ip),
      ),
    );
}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.gray,
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 24.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children:[
              const SizedBox(height: Sizes.largePaddingSpace),
              Image.asset('assets/appLogo_red_black.png', height: Sizes.logoSplashSize,),
              const SizedBox(height: Sizes.smallPaddingSpace),
              const Text(
                    'SmarTrip',
                    style: FontStyles.titleRed,
                  ),
              const SizedBox(height: Sizes.largePaddingSpace),
              TextField(
                        style: FontStyles.signinText,
                        controller: _idController,
                        decoration: const InputDecoration(
                          hintText: 'indirizzo IP:porta',
                          hintStyle: FontStyles.signinText,
                          focusedBorder: UnderlineInputBorder(
                            borderSide: BorderSide(color: AppColors.black),
                          ),
                        ),
                      ),
              const SizedBox(height: Sizes.smallPaddingSpace),
              ElevatedButton(
                  onPressed: () {
                      FocusScope.of(context).unfocus(); // Nasconde la tastiera
                      var ip = _idController.text;
                      navigateToLoginPage(ip);
                  },
                  style: ElevatedButton.styleFrom(
                    backgroundColor: AppColors.red,
                    minimumSize: const Size(double.infinity, 50), // Larghezza bottone
                    shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                          ),
                  ),
                  child:
                    const Text(
                      'CONFERMA',
                      style: FontStyles.buttonTextWhite,
                      ),
                ),
            ] 
            
          ),
        ),
      ),
    );
  }
}