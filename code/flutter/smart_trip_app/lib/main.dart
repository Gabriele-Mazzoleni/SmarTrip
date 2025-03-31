import 'package:flutter/material.dart';
import 'package:smart_trip_app/Presentation/Pages/splash_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: SplashPage(),  // Imposta come pagina iniziale il logo dell'app
    );
  }
}

