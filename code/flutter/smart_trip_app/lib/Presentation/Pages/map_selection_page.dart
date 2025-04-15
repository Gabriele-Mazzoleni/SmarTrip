import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/mappa.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Presentation/Pages/city_selection_page.dart';
import 'package:smart_trip_app/Presentation/Pages/login_page.dart';
//import 'package:smart_trip_app/Presentation/Pages/trip_page.dart';
//import 'package:smart_trip_app/Presentation/Controllers/map_selection_page_controller.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';

class MapSelectionPage extends StatefulWidget {
  final User user;
  final String ip;
  const MapSelectionPage({super.key,required this.user, required this.ip});

  @override
  // ignore: library_private_types_in_public_api
  _MapSelectionPageState createState() => _MapSelectionPageState();
}

class _MapSelectionPageState extends State<MapSelectionPage>{

  bool isLoading=false;
  late List<Mappa> mappeUtente=[Mappa(nomeUtente: widget.user.username,idMappa: 'Bergamo', giorni: 3),Mappa(nomeUtente: widget.user.username,idMappa: 'Milano', giorni: 2)];
  Mappa? _selectedMappa;

    @override
  void initState() {
    super.initState();
    _caricaMappe(widget.user.username);
  }

     void navigateToCitySelectionPage(user, ip) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => CitySelectionPage(user: user, ip:ip),
      ),
    );
  }

  //Trip page ancora non implementata
  /*
  void navigateToTripPage(user, ip, mappa) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => TripPage(user: user, ip:ip, mappa:mappa),
      ),
    );
  }
  */

  Future<void> _caricaMappe(String mail) async {
    setState(() {
      isLoading = true;
    });

    //mappeUtente= await retrieveUserMaps(widget.user.username,widget.ip);

    setState(() {
      isLoading = false;
    });
  }

  //metodo per selezione/deselezione mappa
  void _selezionaMappa(Mappa mappa) {
    setState(() {
      _selectedMappa = (_selectedMappa == mappa) ? null : mappa; 
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.gray,
      resizeToAvoidBottomInset: false,
      body: Column(
        children: [
          // Header della pagina
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
                    const Spacer(),
                    Text(
                      'Benvenuto ${widget.user.username}',
                      style: FontStyles.headerTitle,
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(width: Sizes.largePaddingSpace),
                    IconButton(
                      onPressed: () {
                        Navigator.of(context).pushAndRemoveUntil(
                          MaterialPageRoute(
                            builder: (context) => LoginPage(ip: widget.ip),
                          ),
                          (Route<dynamic> route) => false,
                        );
                      },
                      icon: const Icon(
                        Icons.logout_rounded,
                        color: AppColors.white,
                        size: Sizes.smallIconSize,
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: Sizes.stdPaddingSpace),
                const Text(
                  'Seleziona il piano di viaggio che desideri vedere, o creane uno nuovo',
                  style: FontStyles.headerSubTitle,
                  textAlign: TextAlign.center,
                ),
              ],
            ),
          ),

          // Corpo della pagina
          Expanded(
            child: isLoading
                ? const Center(
                    child: CircularProgressIndicator(
                      color: AppColors.red,
                    ),
                  )
                : mappeUtente.isNotEmpty
                    ? Column(
                        children: [
                          const Column(
                            children: [
                            SizedBox(height: Sizes.largePaddingSpace),
                            Text(
                              'I tuoi piani di viaggio: ',
                                style: FontStyles.noMapsText,
                                textAlign: TextAlign.center,
                            ),
                            SizedBox(height: Sizes.smallPaddingSpace),
                            ],
                          ),
                          Expanded(
                            child: ListView.builder(
                              itemCount: mappeUtente.length,
                              itemBuilder: (context, index) {
                                final mappa = mappeUtente[index];
                                final isSelected = _selectedMappa == mappa;
                                return GestureDetector(
                                  onTap: () => _selezionaMappa(mappa),
                                  child: Card(
                                    shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(10),
                                      side: BorderSide(
                                        color: isSelected
                                            ? AppColors.red
                                            : Colors.transparent,
                                        width: 2,
                                      ),
                                    ),
                                    child: Padding(
                                      padding: const EdgeInsets.fromLTRB(Sizes.stdPaddingSpace,Sizes.smallPaddingSpace,Sizes.stdPaddingSpace,Sizes.smallPaddingSpace),
                                      child: Row(
                                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                        children: [
                                          Text(
                                            mappa.idMappa,
                                            style: FontStyles.cardTitle,
                                          ),
                                          Text(
                                            'Durata in giorni: ${mappa.giorni}',
                                            style: FontStyles.cardText,
                                          ),
                                        ],
                                      ),
                                    ),
                                  ),
                                );
                              },
                            ),
                          ),

                          // Pulsante di conferma
                          Padding(
                            padding: const EdgeInsets.all(Sizes.paddingSpaceFooter),
                            child: ElevatedButton(
                              style: ElevatedButton.styleFrom(
                                backgroundColor: _selectedMappa != null
                                    ? AppColors.red
                                    : Colors.grey,
                                shape: RoundedRectangleBorder(
                                  borderRadius:
                                      BorderRadius.circular(Sizes.smallRoundedCorner),
                                ),
                              ),
                              onPressed: _selectedMappa != null
                                  ? () {
                                      // Azione di conferma (da definire)
                                      //navigateToTripPage(_selectedMappa);
                                      print("Mappa selezionata: ${_selectedMappa!.idMappa}");
                                    }
                                  : null,
                              child: const Text(
                                'CONFERMA',
                                style: FontStyles.buttonTextWhite,
                              ),
                            ),
                          ),
                        ],
                      )
                    : const Column(
                        children: [
                          SizedBox(height: Sizes.largePaddingSpace),
                          Text(
                            'Non hai ancora creato piani di viaggio, creane uno premendo il bottone qui sotto!',
                            style: FontStyles.noMapsText,
                            textAlign: TextAlign.center,
                          ),
                        ],
                      ),
          ),

          // Footer della pagina
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
                    backgroundColor: AppColors.red,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                    ),
                  ),
                  onPressed: () {
                    navigateToCitySelectionPage(widget.user, widget.ip);
                  },
                  child: const Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Text('CREA NUOVO', style: FontStyles.buttonTextWhite),
                      Icon(Icons.add, color: AppColors.white, size: Sizes.smallIconSize),
                    ],
                  ),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }
}