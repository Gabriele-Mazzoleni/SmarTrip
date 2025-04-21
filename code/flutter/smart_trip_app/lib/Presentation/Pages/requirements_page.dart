import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Domain/luogo.dart';
import 'package:smart_trip_app/Presentation/Controllers/requirements_page_controller.dart';
import 'package:smart_trip_app/Presentation/Pages/location_page.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';

class RequirementsPage extends StatefulWidget {
  final User user;
  final String city;
  final String ip;
  final List<Luogo> luoghiSelezionati;
  const RequirementsPage({super.key,required this.user,required this.city, required this.luoghiSelezionati, required this.ip});

  @override
  // ignore: library_private_types_in_public_api
  _RequirementsPageState createState() => _RequirementsPageState();
}

class _RequirementsPageState extends State<RequirementsPage>{

  bool isLoading=false;
  List<Luogo> luoghiSelezionati=[];

  //controller per i campi di testo
  final TextEditingController _mapNameController = TextEditingController();
  final TextEditingController _latitudeController = TextEditingController();
  final TextEditingController _longitudeController = TextEditingController();

  @override
  void initState() {
    super.initState();
  }

  String formattaDurata(int secondiTotali) {
  final ore = secondiTotali ~/ 3600; // divisione intera
  final minuti = (secondiTotali  ~/ 60) - (ore*60);

  final parteOre = ore > 0 ? '$ore ${ore == 1 ? "ora" : "ore"}' : '';
  final parteMinuti = minuti > 0 ? '$minuti ${minuti == 1 ? "minuto" : "minuti"}' : '';

  if (parteOre.isNotEmpty && parteMinuti.isNotEmpty) {
    return '$parteOre e $parteMinuti';
  } else if (parteOre.isNotEmpty) {
    return parteOre;
  } else if (parteMinuti.isNotEmpty) {
    return parteMinuti;
  } else {
    return '0 minuti';
  }
}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
       backgroundColor: AppColors.gray,
    resizeToAvoidBottomInset: false,
    body: Column(
      children: [
        //header della pagina
        Container(
          decoration: const BoxDecoration(
            color: AppColors.red,
            borderRadius: BorderRadius.only(
              bottomLeft: Radius.circular(Sizes.smallRoundedCorner),
              bottomRight: Radius.circular(Sizes.smallRoundedCorner),
            ),
          ),
          padding: const EdgeInsets.all(20.0),
          width: double.infinity,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  IconButton(
                  onPressed: () {
                          Navigator.of(context).pushAndRemoveUntil(
                            MaterialPageRoute(
                                builder: (context) => LocationPage(ip: widget.ip, city: widget.city, user:widget.user,)),
                            (Route<dynamic> route) => false,
                          );
                        },
                  icon: const Icon(
                    Icons.arrow_back,
                    color: AppColors.white, size: Sizes.smallIconSize
                    ),
                ),
                  //const Spacer(),
                  const Text(
                    'DEFINISCI I REQUISITI',
                    style: FontStyles.headerTitle,
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(width: Sizes.largePaddingSpace),
                ],
              ),
              const SizedBox(height:Sizes.stdPaddingSpace),
              const Text(
                'Abbiamo quasi finito, indica per favore i seguenti dati',
                style: FontStyles.headerSubTitle,
                textAlign: TextAlign.center,
              ),
            ],
          ),
        ),
        //corpo della pagina
        Expanded(
          child:Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                const SizedBox(height:Sizes.largePaddingSpace),
                const Text(
                  'Nome dell\' itinerario:',
                  style: FontStyles.noMapsText,
                ),
                TextField(
                  style: FontStyles.signinText,
                  controller: _mapNameController,
                  decoration: const InputDecoration(
                    hintText: 'Nome del tuo itinerario',
                    hintStyle: FontStyles.signinText,
                    focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: AppColors.black),
                    ),
                  ),
                ),
                const SizedBox(height:Sizes.smallPaddingSpace),
                const Text(
                  'Coordinate dell\'alloggio di partenza',
                  style: FontStyles.noMapsText,
                ),
                TextField(
                  style: FontStyles.signinText,
                  controller: _latitudeController,
                  decoration: const InputDecoration(
                    hintText: 'Latitudine',
                    hintStyle: FontStyles.signinText,
                    focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: AppColors.black),
                    ),
                  ),
                ),
                TextField(
                  style: FontStyles.signinText,
                  controller: _longitudeController,
                  decoration: const InputDecoration(
                    hintText: 'Longitudine',
                    hintStyle: FontStyles.signinText,
                    focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: AppColors.black),
                    ),
                  ),
                ),
                    
              ],
            ),
          ),

        ),
        
        //footer della pagina
        Container(
          decoration: const BoxDecoration(
            color: AppColors.black,
            borderRadius: BorderRadius.only(
              topLeft: Radius.circular(Sizes.smallRoundedCorner),
              topRight: Radius.circular(Sizes.smallRoundedCorner),
            ),
          ),
          padding: const EdgeInsets.all(Sizes.paddingSpaceFooter),
          width: double.infinity,
          child: Column(
            children: [
              ElevatedButton(
                style: ElevatedButton.styleFrom(
                    backgroundColor: luoghiSelezionati.isNotEmpty
                      ?AppColors.red
                      :AppColors.gray,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                    ),
                ),
                onPressed: luoghiSelezionati.isNotEmpty
                ?(){
                  //naviga a pagina della definizione requisiti
                  //navigateToRequirementsPage(widget.user, luoghiSelezionati, widget.ip);
                }
                :(){
                  //non fa nulla
                },
                child: const Text('CONTINUA', style: FontStyles.buttonTextWhite),
              )
            ],
            )
        )
      
      ],
    ),
    );
  }

}