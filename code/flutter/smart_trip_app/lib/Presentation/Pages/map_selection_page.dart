import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/mappa.dart';
import 'package:smart_trip_app/Domain/user.dart';
import 'package:smart_trip_app/Presentation/Pages/login_page.dart';
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
  late List<Mappa> mappeUtente;

    @override
  void initState() {
    super.initState();
    _caricaMappe(widget.user.username);
  }

  Future<void> _caricaMappe(String mail) async {
    setState(() {
      isLoading = true;
    });

    //mappeUtente = await retrieveUserMaps(widget.user.username,widget.ip);

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
              Text(
                'Benvenuto ${widget.user.username}',
                style: FontStyles.headerTitle,
                textAlign: TextAlign.center,
              ),
              const SizedBox(height:Sizes.stdPaddingSpace),
              const Text(
                'Seleziona il piano di viaggio che desideri vedere, o creane uno nuovo',
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
            : ListView.builder(
                itemBuilder: (BuildContext context, int index) {  
                  //qui compare la lista di mappe, da implementare una volta realizzata l'API adatta
                },
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
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Align(
                alignment: Alignment.centerLeft,
                child: IconButton(
                  onPressed: () {
                          Navigator.of(context).pushAndRemoveUntil(
                            MaterialPageRoute(
                                builder: (context) => LoginPage(ip: widget.ip,)),
                            (Route<dynamic> route) => false,
                          );
                        },
                  icon: const Icon(
                    Icons.logout_rounded,
                    color: AppColors.white, size: Sizes.iconSize
                    ),
                ),
              ),
              Column(
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
                    child: const Row(
                      children: [
                        Text('CREA NUOVO', style: FontStyles.buttonTextWhite),
                        Icon(Icons.add, color:AppColors.white, size:Sizes.smallIconSize),
                      ],
                    ),
                  )
                ],
                )
              
            ],
            )
        )
      
      ],
    ),
  );
  }
}