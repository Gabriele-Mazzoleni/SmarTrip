import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Presentation/Pages/login_page.dart';
import 'package:smart_trip_app/Presentation/Pages/map_selection_page.dart';
//import 'package:smart_trip_app/Presentation/Controllers/city_selection_page_controller.dart';
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

    @override
  void initState() {
    super.initState();
    _caricaCity(widget.user.username);
  }

  Future<void> _caricaCity(String mail) async {
    setState(() {
      isLoading = true;
    });

    //cities= await retrievecities(widget.user.username,widget.ip);

    setState(() {
      isLoading = false;
    });
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
                  const Spacer(),
                  const Text(
                    'SELEZIONA LA CITTA\'',
                    style: FontStyles.headerTitle,
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(width: Sizes.largePaddingSpace),
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
                itemBuilder: (BuildContext context, int index) {  
                  //qui compare la lista di mappe, da implementare una volta realizzata l'API adatta
                  itemCount: cities.length;
                  

                },
              )
              :const Column(
                children: [
                  SizedBox(height:Sizes.largePaddingSpace),
                  Text(
                    'PAGINA IN COSTRUZIONE',
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
                    backgroundColor: AppColors.red,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                    ),
                ),
                onPressed:  (){
                  
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