import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/mappa.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Domain/luogo.dart';
import 'package:smart_trip_app/Domain/velocita.dart';
import 'package:smart_trip_app/Presentation/Pages/location_page.dart';
import 'package:smart_trip_app/Presentation/Pages/requirements_sub_page.dart';
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

  //controller per i campi del form
  final TextEditingController _mapNameController = TextEditingController();
  final TextEditingController _latitudeController = TextEditingController();
  final TextEditingController _longitudeController = TextEditingController();
  int? numGiorni;
  VelocitaSpostamenti? selectedSpeed;
  final List<VelocitaSpostamenti> speedOptions = VelocitaSpostamenti.values;



  @override
  void initState() {
    super.initState();
  }

  bool formCompleta(){
    return (numGiorni!=null && _mapNameController.text.isNotEmpty && _latitudeController.text.isNotEmpty && _longitudeController.text.isNotEmpty && selectedSpeed!=null);
  }

  void navigateToRequirementsSubPage(User user, Mappa mappaProv, String citta, String ip) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => RequirementsSubPage(user: user, mappa:mappaProv, city:citta ,ip:ip),
      ),
    );
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
                    'Definisci i requisiti',
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
            padding: const EdgeInsets.all(Sizes.stdPaddingSpace),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                const Align(
                  alignment:Alignment.centerLeft,
                  child: Text(
                    'Nome dell\' itinerario:',
                    style: FontStyles.noMapsText,
                  ),
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

                const SizedBox(height:Sizes.largePaddingSpace),
                const Align(
                  alignment:Alignment.centerLeft,
                  child: Text(
                    'Coordinate dell\'alloggio di partenza',
                    style: FontStyles.noMapsText,
                  ),
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

                const SizedBox(height:Sizes.stdPaddingSpace),
                const Align(
                  alignment: Alignment.centerLeft,
                  child: Text(
                    'Numero di giorni previsti di viaggio',
                    style: FontStyles.noMapsText,
                  ),
                ),
                DropdownButtonFormField<int>(
                  value: numGiorni,
                  items: List.generate(10, (index) {
                    int value = index + 1;
                    return DropdownMenuItem<int>(
                      value: value,
                      child: Text(value.toString()),
                      );
                  }),
                  onChanged: (int? newValue) {
                    setState(() {
                      numGiorni = newValue;
                    });
                  },
                  decoration: InputDecoration(
                    hintText: 'numero giorni',
                    hintStyle: FontStyles.signinText,
                    focusedBorder: OutlineInputBorder(
                      borderSide: const BorderSide(color: AppColors.black),
                      borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                    ),
                  ),
                  style: FontStyles.signinText,
                ),

                const SizedBox(height:Sizes.stdPaddingSpace),
                const Align(
                  alignment: Alignment.centerLeft,
                  child: Text(
                    'Rapidità degli spostamenti',
                    style: FontStyles.noMapsText,
                  ),
                ),
                const SizedBox(height:Sizes.smallPaddingSpace),
                DropdownButtonFormField<VelocitaSpostamenti>(
                  value: selectedSpeed,
                  items: speedOptions.map((VelocitaSpostamenti speed) {
                    return DropdownMenuItem<VelocitaSpostamenti>(
                      value: speed,
                      child: Text(speed.label, style: FontStyles.signinText),
                    );
                  }).toList(),
                  onChanged: (VelocitaSpostamenti? newValue) {
                    setState(() {
                      selectedSpeed = newValue;
                    });
                  },
                  decoration: InputDecoration(
                    hintText: 'Seleziona velocità',
                    hintStyle: FontStyles.signinText,
                    focusedBorder: OutlineInputBorder(
                      borderSide: const BorderSide(color: AppColors.black),
                      borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                    ),
                  ),
                  style: FontStyles.signinText,
                )
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
                    backgroundColor: formCompleta()
                      ?AppColors.red
                      :AppColors.gray,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                    ),
                ),
                onPressed: formCompleta()
                ?(){
                  Mappa mappaProv= Mappa( 
                    nomeUtente: widget.user.username, 
                    idMappa: _mapNameController.text, 
                    latAlloggio: double.parse(_latitudeController.text), 
                    longAlloggio: double.parse(_longitudeController.text), 
                    numGiorni: numGiorni!, 
                    velMedia: selectedSpeed!.valore, 
                    luoghi: widget.luoghiSelezionati, 
                    giornate: [/*Per ora vuoti, li si definirà alla prossima pagina*/]);

                  //naviga a pagina della definizione requisiti singole giornate
                  navigateToRequirementsSubPage(widget.user, mappaProv,widget.city, widget.ip);
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