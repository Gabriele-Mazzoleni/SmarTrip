import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Domain/luogo.dart';
import 'package:smart_trip_app/Presentation/Pages/city_selection_page.dart';
import 'package:smart_trip_app/Presentation/Controllers/location_page_controller.dart';
import 'package:smart_trip_app/Presentation/Pages/requirements_page.dart';
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
  List<Luogo> luoghiSelezionati=[];

  @override
  void initState() {
    super.initState();
    _caricaLocations();
  }

    Future<void> _caricaLocations() async {
    setState(() {
      isLoading = true;
    });

    luoghi= await retrieveLocations(widget.city, widget.ip);

    setState(() {
      isLoading = false;
    });
  }

  void toggleSelezioneLuogo(Luogo l){
    if(luoghiSelezionati.contains(l)){
      luoghiSelezionati.remove(l);
    }
    else{
      luoghiSelezionati.add(l);
    }
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

  void navigateToRequirementsPage(user,city, localita, ip) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => RequirementsPage(user: user, city:city, luoghiSelezionati: localita, ip:ip),
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
                                builder: (context) => CitySelectionPage(ip: widget.ip, user:widget.user,)),
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
                    'SELEZIONA I LUOGHI',
                    style: FontStyles.headerTitle,
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(width: Sizes.largePaddingSpace),
                ],
              ),
              const SizedBox(height:Sizes.stdPaddingSpace),
              const Text(
                'Seleziona i luoghi che ti piacerebbe visitare per questo viaggio',
                style: FontStyles.headerSubTitle,
                textAlign: TextAlign.center,
              ),
            ],
          ),
        ),
        //corpo della pagina
        Expanded(
          child: isLoading
            ? const Center(
                        child: CircularProgressIndicator(
                          color: AppColors.red,
                        ),
                    )
            : luoghi.isNotEmpty?
              ListView.builder(
                itemCount: luoghi.length,
                itemBuilder: (context, index) {
                  final luogo = luoghi[index];
                  final isSelected = luoghiSelezionati.contains(luogo);
                  return Card(
                    margin: const EdgeInsets.symmetric(vertical: 8.0, horizontal: 12),
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(10), 
                    ),
                    child: Padding(
                      padding: const EdgeInsets.all(Sizes.smallPaddingSpace),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Row(
                            children: [
                              Expanded(
                                child: Text(
                                    luogo.nome,
                                    style: FontStyles.cardTitle,
                                  ),
                              ),
                              const SizedBox(width: Sizes.smallPaddingSpace),
                              Checkbox(
                                value: isSelected,
                                onChanged: (_) {
                                  setState(() {
                                    toggleSelezioneLuogo(luogo);
                                  });
                                },
                                activeColor: AppColors.red
                              ), 
                            ],
                          ),
                          Text(
                            luogo.indirizzo,
                            style:FontStyles.cardText
                          ),
                          Text(
                            'Tempo di visita previsto: ${formattaDurata(luogo.tempoVisita)}',
                            style:FontStyles.cardText
                          ),
                          const SizedBox(height: Sizes.smallPaddingSpace),
                          SizedBox(
                            child: Image.network(
                              luogo.immagine,
                              fit: BoxFit.fill,
                            ),
                          ),
                        ],
                      ),
                    ),
                  );
                },
              )
              :const Column(
                children: [
                  SizedBox(height:Sizes.largePaddingSpace),
                  Text(
                    'ERRORE NEL CARICAMENTO DELLE CITTA\' DAL DATABASE',
                    style: FontStyles.noMapsText,
                    textAlign: TextAlign.center,
                  ),
                ],
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
                  navigateToRequirementsPage(widget.user, widget.city, luoghiSelezionati, widget.ip);
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