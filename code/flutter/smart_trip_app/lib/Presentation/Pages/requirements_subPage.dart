import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Domain/luogo.dart';
import 'package:smart_trip_app/Domain/velocita.dart';
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

  
  @override
  void initState() {
    super.initState();
  }

  bool isLoading=false;
  bool showSubforms = false;

  //controller per i campi del form della prima sezione
  final TextEditingController _mapNameController = TextEditingController();
  final TextEditingController _latitudeController = TextEditingController();
  final TextEditingController _longitudeController = TextEditingController();
  int? numGiorni;
  VelocitaSpostamenti? selectedSpeed;
  final List<VelocitaSpostamenti> speedOptions = VelocitaSpostamenti.values;

  //controller per le sottoform delle singole giornate
  List<TextEditingController> subformControllers = [];

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

bool formCompleta(){
  return (numGiorni!=null && _mapNameController.text.isNotEmpty && _latitudeController.text.isNotEmpty && _longitudeController.text.isNotEmpty && selectedSpeed!=null);
}

 bool giornateDefinite=false;

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
            padding: const EdgeInsets.all(Sizes.stdPaddingSpace),
            child: showSubforms //se true, siamo nella seconda sottopagina, di impostazione delle giornate
            ? ListView.builder(
            itemCount: numGiorni ?? 0,
            itemBuilder: (context, index) {
              return Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Attività per il giorno ${index + 1}', style: FontStyles.noMapsText),
                  const SizedBox(height: Sizes.smallPaddingSpace),
                  TextField(
                    controller: subformControllers[index],
                    style: FontStyles.signinText,
                    decoration: const InputDecoration(
                      hintText: 'Descrivi le attività',
                      hintStyle: FontStyles.signinText,
                      focusedBorder: OutlineInputBorder(
                        borderSide: BorderSide(color: AppColors.black),
                      ),
                    ),
                  ),
                  const SizedBox(height: Sizes.stdPaddingSpace),
                ],
              );
            },
          )
            : Column(
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
              if (showSubforms) //se ci troviamo nella seconda sottopagina
                ElevatedButton(
                  onPressed: () {
                    setState(() {
                      showSubforms = false;
                    });
                  },
                  style: ElevatedButton.styleFrom(backgroundColor: AppColors.red),
                  child: const Text('INDIETRO', style: FontStyles.buttonTextWhite),
                ),
              const SizedBox(height: Sizes.smallPaddingSpace),
              ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: formCompleta() ? AppColors.red : AppColors.gray,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                  ),
                ),
                onPressed: (!showSubforms) //se siamo nella prima sottopagina passa alla seconda
                  ?formCompleta()
                    ? () {
                      setState(() {
                        subformControllers = List.generate(numGiorni!, (_) => TextEditingController());
                        showSubforms = true;
                      });
                    }
                    : null
                  : null, //passa alla prossima pagina, che devo ancora implementare
                child: const Text('CONTINUA', style: FontStyles.buttonTextWhite),
              )
            ],
          ),
        ) 
      ],
    ),
    );
  }

}