import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/itinerario.dart';
import 'package:smart_trip_app/Domain/luogo_esteso.dart';
import 'package:smart_trip_app/Domain/mappa.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Presentation/Controllers/trip_page_controller.dart';
import 'package:smart_trip_app/Presentation/Pages/map_selection_page.dart';
import 'package:smart_trip_app/Presentation/Pages/requirements_page.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';

class TripPage extends StatefulWidget {
  final User user;
  final String ip;
  final Mappa?  mappa;
  final String? mapName;
  final int newOrOld; //0 = mappa nuova, 1= mappa pre esistente

  const TripPage({super.key,required this.user, required this.mappa, required this.mapName, required this.ip, required this.newOrOld});

  @override
  // ignore: library_private_types_in_public_api
  _TripPageState createState() => _TripPageState();
}

class _TripPageState extends State<TripPage>{

  late Itinerario itinerario;

    @override
  void initState() {
    super.initState();
    _caricaItinerario();
  }

      Future<void> _caricaItinerario() async {
    setState(() {
      isLoading = true;
    });
    if (widget.newOrOld == 0 && widget.mappa != null) {
      // Mappa nuova, generata da RequirementsSubPage
      itinerario = await ottieniItinerarioDaNuovaMappa(widget.mappa!, widget.ip);
    } else if (widget.newOrOld == 1 && widget.mapName != null) {
      // Mappa preesistente, selezionata da MapSelectionPage
      itinerario = await ottieniItinerarioDaVecchiaMappa(widget.ip, widget.mapName!,widget.user.username);
    } else {
    // Caso di errore o mancanza dati
    throw Exception("Dati insufficienti per caricare l'itinerario");
  }

    setState(() {
      isLoading = false;
    });
  }

  bool isLoading=false;

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
          child: const Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                'LA TUA TABELLA DI MARCIA',
                style: FontStyles.headerTitle,
                textAlign: TextAlign.center,
              ),
              SizedBox(width: Sizes.largePaddingSpace),
              SizedBox(height:Sizes.stdPaddingSpace),
              Text(
                'In base alle location e ai requisiti da te inseriti, ecco una sequenza ottimale dei luoghi da visitare',
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
            : PageView.builder(
              itemCount:itinerario.giorniViaggio.keys.length,
              itemBuilder: (context, pageIndex){
                String giorno=itinerario.giorniViaggio.keys.elementAt(pageIndex);
                List<LuogoEsteso> luoghi= itinerario.giorniViaggio[giorno]!;
            
                return Stack(
                  children: [
                    Positioned.fill(
                      child: Image.asset(
                        'assets/lineaTratteggiata.png',
                        fit: BoxFit.cover,
                      ),
                    ),
                    Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children:[
                        const SizedBox(height:Sizes.stdPaddingSpace),
                        Container(
                          decoration: const BoxDecoration(
                            color: AppColors.red,
                            borderRadius: BorderRadius.all(
                              Radius.circular(Sizes.smallRoundedCorner)
                            ),
                          ),
                          padding: const EdgeInsets.all(Sizes.smallPaddingSpace),
                          child: Text(
                            "Giorno $giorno/${itinerario.giorniViaggio.keys.length}",
                            style: FontStyles.headerTitle,
                          ),
                        ),
                        Expanded(
                          child: ListView.builder(
                            itemCount: luoghi.length,
                            itemBuilder: (context,index){
                              LuogoEsteso luogoEst= luoghi[index];
                              return Card(
                                margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                                child: ListTile(
                                  title: Text(luogoEst.luogo.nome, style:FontStyles.cardTitle),
                                  subtitle: Align(
                                    alignment: Alignment.centerLeft,
                                    child: Column(
                                      crossAxisAlignment: CrossAxisAlignment.start,
                                      children: [
                                        Text(luogoEst.luogo.indirizzo, style:FontStyles.cardText),
                                        Text('Orario di arrivo prevista: ${formatTimeOfDay(luogoEst.oraArrivo)}', style:FontStyles.cardText),
                                        Text('Durata media visita: ${luogoEst.luogo.tempoVisita/60} minuti', style:FontStyles.cardText),
                                    ],),
                                  )
                                ),
                              );
                            }
                          )
                      )
                    ]
                  ),
                  ]
                  
                );
              }
              )

        ),

        //footer della pagina
        
        Container(
          decoration: const BoxDecoration(
            color: AppColors.gray,
          ),
          padding: const EdgeInsets.all(Sizes.paddingSpaceFooter),
          width: double.infinity,
          child: Column(
            children: [
              ElevatedButton(
                style: ElevatedButton.styleFrom(
                    backgroundColor:AppColors.red,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                    ),
                ),
                onPressed: () {
                    Navigator.of(context).pushAndRemoveUntil(
                      MaterialPageRoute(
                        builder: (context) => RequirementsPage(ip: widget.ip, 
                                                              user:widget.user, 
                                                              city: itinerario.getCity(), 
                                                              luoghiSelezionati:itinerario.getLuoghi())),
                      (Route<dynamic> route) => false,
                    );
                },
                child: const Text('Modifica Requisiti', style: FontStyles.buttonTextWhite),
              ),
              ElevatedButton(
                style: ElevatedButton.styleFrom(
                    backgroundColor:AppColors.black,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                    ),
                ),
                onPressed:(){
                  //torna a pagina della selezione mappe
                  Navigator.of(context).pushAndRemoveUntil(
                      MaterialPageRoute(
                        builder: (context) => MapSelectionPage(ip: widget.ip, 
                                                              user:widget.user,)),
                      (Route<dynamic> route) => false,
                    );
                },
                child: const Text('Seleziona un altro itinerario', style: FontStyles.buttonTextWhite),
              )
            ],
            )
        )
        
      
      ],
    ),
    );
  }

}
