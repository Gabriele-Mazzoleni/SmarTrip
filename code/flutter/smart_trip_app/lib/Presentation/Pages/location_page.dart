import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Domain/luogo.dart';
import 'package:smart_trip_app/Presentation/Pages/city_selection_page.dart';
import 'package:smart_trip_app/Presentation/Controllers/location_page_controller.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';

class LocationPage extends StatefulWidget {
  final User user;
  final String city;
  final String ip;
  const LocationPage({super.key,required this.user,required this.city, required this.ip});

  @override
  // ignore: library_private_types_in_public_api
  _LocationPageState createState() => _LocationPageState();
}

class _LocationPageState extends State<LocationPage>{

  bool isLoading=false;
  List<Luogo> luoghi=[];

  @override
  void initState() {
    super.initState();
    _caricaLocations();
  }

    Future<void> _caricaLocations() async {
    setState(() {
      isLoading = true;
    });

    //luoghi= await retrieveLocations(vidget.city, widget.ip);

    setState(() {
      isLoading = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return const Scaffold();
  }

}