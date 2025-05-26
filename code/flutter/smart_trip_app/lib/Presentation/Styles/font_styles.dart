import 'package:flutter/material.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';

class FontStyles{
  FontStyles._();
  
  //titoli splash page
  static const TextStyle title= TextStyle(color: AppColors.black, fontWeight: FontWeight.bold, fontSize: 50, fontFamily:'Verdana');
  static const TextStyle titleRed= TextStyle(color: AppColors.red, fontWeight: FontWeight.bold, fontSize: 50, fontFamily:'Verdana');
  //titoli loginPage
  static const TextStyle loginTitle= TextStyle(color: AppColors.black, fontWeight: FontWeight.bold, fontSize: 25, fontFamily:'Verdana');
  static const TextStyle loginTitleRed= TextStyle(color: AppColors.red, fontWeight: FontWeight.bold, fontSize: 35, fontFamily:'Verdana');
  //stili titoli altre pagine
  static const TextStyle headerTitle= TextStyle(color: AppColors.white, fontWeight: FontWeight.bold, fontSize: 23, fontFamily:'Verdana'); 
  static const TextStyle headerSubTitle= TextStyle(color: AppColors.white, fontWeight: FontWeight.normal, fontSize: 12, fontFamily:'Verdana');
  //stili testo per dati login
  static const TextStyle loginText= TextStyle(color: AppColors.black, fontWeight: FontWeight.normal, fontSize: 14, fontFamily:'Verdana');
  static const TextStyle signinText= TextStyle(color: AppColors.black, fontWeight: FontWeight.normal, fontSize: 14, fontFamily:'Verdana');
  //stili pulsanti
  static const TextStyle buttonTextBlack= TextStyle(color: AppColors.black, fontWeight: FontWeight.bold, fontSize: 20, fontFamily:'Verdana' );
  static const TextStyle buttonTextRed= TextStyle(color: AppColors.red, fontWeight: FontWeight.bold, fontSize: 20, fontFamily:'Verdana' );
  static const TextStyle buttonTextWhite= TextStyle(color: AppColors.white, fontWeight: FontWeight.bold, fontSize: 20, fontFamily:'Verdana' );
  static const TextStyle smallButtonTextWhite= TextStyle(color: AppColors.white, fontWeight: FontWeight.bold, fontSize: 17, fontFamily:'Verdana' );

  //stili testi vari
  static const TextStyle subTitle= TextStyle(color: AppColors.black, fontWeight: FontWeight.bold, fontSize: 20, fontFamily:'Verdana' );
  static const TextStyle noMapsText= TextStyle(color: AppColors.black, fontWeight: FontWeight.bold, fontSize: 16, fontFamily:'Verdana' );
  static const TextStyle cardTitle= TextStyle(color: AppColors.black, fontWeight: FontWeight.bold, fontSize: 20, fontFamily:'Verdana' );
  static const TextStyle cardText= TextStyle(color: AppColors.black, fontWeight: FontWeight.normal, fontSize: 14, fontFamily:'Verdana' );
  static const TextStyle redCardTitle= TextStyle(color: AppColors.white, fontWeight: FontWeight.bold, fontSize: 20, fontFamily:'Verdana' );
  static const TextStyle redCardText= TextStyle(color: AppColors.white, fontWeight: FontWeight.normal, fontSize: 14, fontFamily:'Verdana' );
  static const TextStyle userParameterText= TextStyle(color: AppColors.black, fontWeight: FontWeight.normal, fontSize: 14, fontFamily:'Verdana' );
  static const TextStyle errorMessageStyle=TextStyle(color: AppColors.white, fontWeight: FontWeight.normal, fontSize: 14, fontFamily:'Verdana');
  static const TextStyle graphicTag= TextStyle(color: AppColors.red, fontWeight: FontWeight.normal, fontSize: 10, fontFamily:'Verdana' );

}