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
  
  @override
  void initState() {
    super.initState();
    giornateForms = List.generate(widget.mappa.numGiorni, (_) => Giornata());
  }

  void navigateToTripPage(User user, Mappa mappa, String citta, String ip) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => TripPage(user: user, mappa:mappa, city:citta ,ip:ip, newOrOld: 0,),
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
                final formData = giornateForms[index];
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
                            formData.oraInizio != null
                            ? 'Partenza: ${formData.oraInizio!.format(context)}'
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
                                formData.oraInizio = time;
                              });
                            }
                          },
                        ),

                        // Devo pranzare
                        SwitchListTile(
                          title: const Text('Devi pranzare?', style: FontStyles.signinText),
                          value: formData.devoPranzare,
                          activeColor: AppColors.red,
                          onChanged: (val) {
                            setState(() => formData.devoPranzare = val);
                          },
                        ),

                        // Ora pranzo
                        if (formData.devoPranzare)
                        ListTile(
                          title: Text(
                            formData.oraPranzo != null
                            ? 'Ora pranzo: ${formData.oraPranzo!.format(context)}'
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
                                  formData.oraPranzo = time;
                                });
                              }
                          },
                        ),

                        // Tempo pranzo
          if (formData.devoPranzare)
            TextField(
              keyboardType: TextInputType.number,
              decoration: const InputDecoration(
                hintText: 'Tempo pranzo (in minuti)',
                hintStyle: FontStyles.signinText,
                focusedBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: AppColors.black),
                ),
              ),
              onChanged: (val) => formData.tempoPranzo = int.tryParse(val),
            ),

          const SizedBox(height: Sizes.smallPaddingSpace),

          // Pausa
          TextField(
            keyboardType: TextInputType.number,
            decoration: const InputDecoration(
              hintText: 'Pausa tra visite (in minuti)',
              hintStyle: FontStyles.signinText,
              focusedBorder: OutlineInputBorder(
                borderSide: BorderSide(color: AppColors.black),
              ),
            ),
            onChanged: (val) => formData.pausa = int.tryParse(val),
          ),

          const SizedBox(height: Sizes.smallPaddingSpace),

          // Tempo visita
          TextField(
            keyboardType: TextInputType.number,
            decoration: const InputDecoration(
              hintText: 'Tempo totale visita (in ore)',
              hintStyle: FontStyles.signinText,
              focusedBorder: OutlineInputBorder(
                borderSide: BorderSide(color: AppColors.black),
              ),
            ),
            onChanged: (val) => formData.tempoVisita = int.tryParse(val*60), //l'utente fornisce il tempo in ore, serve traduzione in minuti
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
                        widget.mappa.giornate=giornateForms;
                        navigateToTripPage(widget.user,widget.mappa,widget.city,widget.ip);
                      });
                    }
                    : (){
                      //se la form non è completa non faccio nulla
                    },
                child: Row(
                  children: [
                    const Text('CALCOLA ITINERARIO', style: FontStyles.buttonTextWhite),
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