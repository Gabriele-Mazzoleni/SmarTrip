import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/giornata.dart';
import 'package:smart_trip_app/Domain/mappa.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Presentation/Controllers/requirements_page_controller.dart';
import 'package:smart_trip_app/Presentation/Pages/requirements_page.dart';
import 'package:smart_trip_app/Presentation/Pages/trip_page.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';

class RequirementsSubPage extends StatefulWidget {
  final User user;
  final String ip;
  final String city;
  final Mappa mappa;

  const RequirementsSubPage({super.key,required this.user, required this.mappa, required this.city, required this.ip});

  @override
  // ignore: library_private_types_in_public_api
  _RequirementsSubPageState createState() => _RequirementsSubPageState();
}

class _RequirementsSubPageState extends State<RequirementsSubPage>{

  late List<Giornata> giornateForms;

  //textController per i campi delle form
  late List<TextEditingController> tempoVisitaControllers;
  late List<TextEditingController> pausaControllers;
  late List<TextEditingController> tempoPranzoControllers;
  
  @override
  void initState() {
    super.initState();
    final numGiorni = widget.mappa.numGiorni;
    giornateForms = List.generate(numGiorni, (_) => Giornata());
    tempoVisitaControllers = List.generate(numGiorni, (_) => TextEditingController());
    pausaControllers = List.generate(numGiorni, (_) => TextEditingController());
    tempoPranzoControllers = List.generate(numGiorni, (_) => TextEditingController());
  }

  void navigateToTripPage(User user, Mappa mappa, String citta, String ip) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => TripPage(user: user, mappa:mappa,ip:ip, newOrOld: 0, mapName: mappa.idMappa,),
      ),
    );
  }

bool formCompleta(){
    for (final giornata in giornateForms) {
    if (giornata.oraInizio == null ||
        giornata.pausa == null ||
        giornata.tempoVisita == null) {
      return false;
    }

    if (giornata.devoPranzare) {
      if (giornata.oraPranzo == null || giornata.tempoPranzo == null) {
        return false;
      }
    }
  }
  return true;
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
                                builder: (context) => RequirementsPage(ip: widget.ip, city: widget.city, user:widget.user, luoghiSelezionati:widget.mappa.luoghi)),
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
                'Per ogni giornata di viaggio inserisci i dati richiesti',
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
            child: 
              ListView.builder(
              itemCount: widget.mappa.numGiorni,
              itemBuilder: (context, index) {
                final giornataFormData = giornateForms[index];
                final pausaController = pausaControllers[index];
                final tempoVisitaController = tempoVisitaControllers[index];
                final tempoPranzoController = tempoPranzoControllers[index];
                
                return Card(
                  margin: const EdgeInsets.symmetric(vertical: 8.0, horizontal: 12),
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
                  child: Padding(
                    padding: const EdgeInsets.all(Sizes.smallPaddingSpace),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text('Giornata ${index + 1}', style: FontStyles.cardTitle),
                        const SizedBox(height: Sizes.smallPaddingSpace),

                        // Orario inizio
                        ListTile(
                          title: Text(
                            giornataFormData.oraInizio != null
                            ? 'Partenza: ${giornataFormData.oraInizio!.format(context)}'
                            : 'Seleziona orario di partenza',
                            style: FontStyles.signinText,
                          ),
                          trailing: const Icon(Icons.access_time),
                          onTap: () async {
                            final time = await showCustomTimePicker(
                              context: context,
                              initialTime: TimeOfDay.now(),
                            );
                            if (time != null) {
                              setState(() {
                                giornataFormData.oraInizio = time;
                              });
                            }
                          },
                        ),

                        // Devo pranzare
                        SwitchListTile(
                          title: const Text('Devi pranzare?', style: FontStyles.signinText),
                          value: giornataFormData.devoPranzare,
                          activeColor: AppColors.red,
                          onChanged: (val) {
                            setState(() => giornataFormData.devoPranzare = val);
                          },
                        ),

                        // Ora pranzo
                        if (giornataFormData.devoPranzare)
                        ListTile(
                          title: Text(
                            giornataFormData.oraPranzo != null
                            ? 'Ora pranzo: ${giornataFormData.oraPranzo!.format(context)}'
                             : 'Seleziona ora del pranzo',
                            style: FontStyles.signinText,
                          ),
                          trailing: const Icon(Icons.access_time),
                          onTap: () async {
                            final time = await showCustomTimePicker(
                              context: context,
                              initialTime: TimeOfDay.now(),
                              );
                              if (time != null) {
                                setState(() {
                                  giornataFormData.oraPranzo = time;
                                });
                              }
                          },
                        ),

                        // Tempo pranzo
          if (giornataFormData.devoPranzare)
            TextField(
              controller: tempoPranzoController,
              keyboardType: TextInputType.number,
              decoration: const InputDecoration(
                hintText: 'Tempo pranzo (in minuti)',
                hintStyle: FontStyles.signinText,
                focusedBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: AppColors.black),
                ),
              ),
              onChanged: (val) {
                final parsed = int.tryParse(val);
                giornataFormData.tempoPranzo = parsed;
              },
            ),

          const SizedBox(height: Sizes.smallPaddingSpace),

          // Pausa
          TextField(
            controller:pausaController,
            keyboardType: TextInputType.number,
            decoration: const InputDecoration(
              hintText: 'Pausa tra visite (in minuti)',
              hintStyle: FontStyles.signinText,
              focusedBorder: OutlineInputBorder(
                borderSide: BorderSide(color: AppColors.black),
              ),
            ),
            onChanged: (val) {
              final parsed = int.tryParse(val);
              giornataFormData.pausa = parsed;
            },
          ),

          const SizedBox(height: Sizes.smallPaddingSpace),

          // Tempo visita
          TextField(
            controller: tempoVisitaController,
            keyboardType: TextInputType.number,
            decoration: const InputDecoration(
              hintText: 'Tempo totale visita (in ore)',
              hintStyle: FontStyles.signinText,
              focusedBorder: OutlineInputBorder(
                borderSide: BorderSide(color: AppColors.black),
              ),
            ),
            onChanged: (val) {
              final parsed = double.tryParse(val);
              if (parsed != null) {
                giornataFormData.tempoVisita = (parsed * 60).toInt(); // ore → minuti
              } else {
                giornataFormData.tempoVisita = null;
              }
            }, //l'utente fornisce il tempo in ore, serve traduzione in minuti
          ),
        ],
      ),
    ),
  );
}

            )
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
              const SizedBox(height: Sizes.smallPaddingSpace),
              ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: formCompleta() ? AppColors.red : AppColors.gray,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                  ),
                ),
                onPressed:formCompleta()
                    ? () {
                        setState(() {
                          for (var giornata in giornateForms) {
                            if (!giornata.devoPranzare) {
                              giornata.oraPranzo = const TimeOfDay(hour: 0, minute: 0);
                              giornata.tempoPranzo = 0;
                            }
                          }
                          widget.mappa.giornate = giornateForms;
                        navigateToTripPage(widget.user, widget.mappa, widget.city, widget.ip);
                        });
                      }
                    : (){
                      //se la form non è completa non faccio nulla
                    },
                child: Row(
                  children: [
                    const Text('CALCOLA ITINERARIO', style: FontStyles.buttonTextWhite),
                    const Spacer(),
                    Image.asset('assets/appIcon.png', height: Sizes.smallIconSize),
                  ],
                ),
              )
            ],
          ),
        ) 
      ],
    ),
    );
  }

}