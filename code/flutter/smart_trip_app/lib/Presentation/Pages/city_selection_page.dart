import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Presentation/Pages/map_selection_page.dart';
import 'package:smart_trip_app/Presentation/Pages/location_page.dart';
import 'package:smart_trip_app/Presentation/Controllers/city_selection_page_controller.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';

class CitySelectionPage extends StatefulWidget {
  final User user;
  final String ip;
  const CitySelectionPage({super.key,required this.user, required this.ip});

  @override
  // ignore: library_private_types_in_public_api
  _CitySelectionPageState createState() => _CitySelectionPageState();
}

class _CitySelectionPageState extends State<CitySelectionPage>{

  bool isLoading=false;
  late List<String> cities=[];
  String _selectedString='';

    @override
  void initState() {
    super.initState();
    _caricaCity();
  }

  Future<void> _caricaCity() async {
    setState(() {
      isLoading = true;
    });

    cities= await retrieveCities(widget.ip);

    setState(() {
      isLoading = false;
    });
  }

  //metodo per selezione/deselezione città
  void _selezionaCity(String city) {
    setState(() {
      _selectedString = (_selectedString == city) ? '' : city; 
    });
  }

  void navigateToLocationPage(user,city, ip) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => LocationPage(user: user, city:city, ip:ip),
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
                                builder: (context) => MapSelectionPage(ip: widget.ip, user:widget.user,)),
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
                    'SELEZIONA LA CITTA\'',
                    style: FontStyles.headerTitle,
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(width: Sizes.largePaddingSpace),
                ],
              ),
              const SizedBox(height:Sizes.stdPaddingSpace),
              const Text(
                'Seleziona la location del tuo viaggio tra le seguenti',
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
            : cities.isNotEmpty?
              ListView.builder(
                itemCount: cities.length,
                itemBuilder: (context, index) {
                  final city = cities[index];
                  final isSelected = _selectedString == city;
                  return GestureDetector(
                    onTap: () => _selezionaCity(city),
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
                              city,
                              style: FontStyles.cardTitle,
                            ),
                          ],
                        ),
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
                    backgroundColor: _selectedString.isNotEmpty
                      ?AppColors.red
                      :AppColors.gray,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                    ),
                ),
                onPressed:  _selectedString.isNotEmpty
                ?(){
                  //naviga a pagina della selezione luoghi
                  navigateToLocationPage(widget.user, _selectedString, widget.ip);
                }
                :(){
                  //non fa nulla
                },
                child: const Text('CONFERMA', style: FontStyles.buttonTextWhite),
              )
            ],
            )
        )
      
      ],
    ),
  );
  }
}