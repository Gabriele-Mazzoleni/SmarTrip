import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/giornata.dart';
import 'package:smart_trip_app/Domain/mappa.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Presentation/Controllers/trip_page_controller.dart';
import 'package:smart_trip_app/Presentation/Pages/requirements_sub_page.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';

class TripPage extends StatefulWidget {
  final User user;
  final String ip;
  final String city;
  final Mappa mappa;

  const TripPage({super.key,required this.user, required this.mappa, required this.city, required this.ip});

  @override
  // ignore: library_private_types_in_public_api
  _TripPageState createState() => _TripPageState();
}

class _TripPageState extends State<TripPage>{
    @override
  Widget build(BuildContext context) {
    return Scaffold(
      //TODO
    );
  }

}
