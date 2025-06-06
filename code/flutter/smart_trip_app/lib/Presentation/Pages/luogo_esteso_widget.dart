
import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/luogo.dart';
import 'package:smart_trip_app/Domain/luogo_esteso.dart';
import 'package:smart_trip_app/Presentation/Controllers/trip_page_controller.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';
import 'package:cached_network_image/cached_network_image.dart';

class LuogoEstesoWidget extends StatefulWidget {
  final LuogoEsteso luogoEst;
  final String ip;


  const LuogoEstesoWidget({super.key, required this.luogoEst, required this.ip});

  @override
  _LuogoEstesoWidgetState createState() => _LuogoEstesoWidgetState();
}

class _LuogoEstesoWidgetState extends State<LuogoEstesoWidget> {

  Luogo? selectedRestaurant; //viene effettivamente istanziato soltanto se il luogo è una selezione ristorante

  @override
  Widget build(BuildContext context) {
    //Tre tipi di card diversa
    if(widget.luogoEst.luogo.tipo=='Default'){
      //tipo 1: per luoghi default -> partenza/arrivo
      return Card(
        margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
        color: AppColors.red,
        child: ListTile(
          title: Text(widget.luogoEst.luogo.nome, style:FontStyles.redCardTitle),
          subtitle: Align(
            alignment: Alignment.centerLeft,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Orario previsto: ${formatTimeOfDay(widget.luogoEst.oraArrivo)}', style:FontStyles.redCardText),
              ],),
            )
      ),
      );
    }
    else if(widget.luogoEst.luogo.tipo=='Ristorante'){
      //tipo 2: per ricerca ristorante
      return Card(
        margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
        child: ListTile(
          title: Text(widget.luogoEst.luogo.nome, style:FontStyles.cardTitle),
          subtitle: Align(
            alignment: Alignment.centerLeft,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                (selectedRestaurant!= null) ? Text('Ristorante selezionato: ${selectedRestaurant?.nome}', style:FontStyles.cardText)
                : const SizedBox(height:Sizes.smallPaddingSpace),

                Text('Orario previsto: ${formatTimeOfDay(widget.luogoEst.oraArrivo)}', style:FontStyles.cardText),
                const SizedBox(height:Sizes.smallPaddingSpace),
                ElevatedButton(
                    onPressed: () {
                      _showRestaurantSelectionModal(context, widget.luogoEst.luogo.latitudine, widget.luogoEst.luogo.longitudine);
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: AppColors.red,
                      minimumSize: const Size(double.infinity, 50), // Larghezza bottone
                      shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                            ),
                    ),
                    child: (selectedRestaurant!= null)
                      ? const Icon(Icons.change_circle_outlined, color:AppColors.white, size:Sizes.smallIconSize)
                      : const Icon(Icons.add, color:AppColors.white, size:Sizes.smallIconSize),
                  ),
              ],),
            )
      ),
      );
    }
    else{
      //tipo 3: pre luoghi visitabili e di ristoro
      return Card(
      margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      child: ListTile(
        title: Text(widget.luogoEst.luogo.nome, style:FontStyles.cardTitle),
        subtitle: Align(
          alignment: Alignment.centerLeft,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(widget.luogoEst.luogo.indirizzo, style:FontStyles.cardText),
              Text('Orario di arrivo prevista: ${formatTimeOfDay(widget.luogoEst.oraArrivo)}', style:FontStyles.cardText),
              Text('Durata media visita: ${widget.luogoEst.luogo.tempoVisita/60} minuti', style:FontStyles.cardText),
          ],),
        )
      ),
    );
    }
  }

Future<void> _showRestaurantSelectionModal(BuildContext context, double lat, double long) async {
  Map<Luogo, double> ristoranti = await getListaRistoranti(widget.ip, lat, long);
  Luogo? selRes;

  // showDialog restituirà il ristorante selezionato
  final Luogo? result = await showDialog<Luogo>(
    context: context,
    builder: (BuildContext context) {
      return StatefulBuilder(
        builder: (context, setState) {
          return AlertDialog(
            title: const Text("Seleziona un ristorante"),
            backgroundColor: AppColors.white,
            content: SizedBox(
              height: 200,
              width: double.maxFinite,
              child: SingleChildScrollView(
                scrollDirection: Axis.horizontal,
                child: Row(
                  children: ristoranti.entries.map((entry) {
                    final luogo = entry.key;
                    final distanza = entry.value;
                    final isSelected = luogo == selRes;

                    return GestureDetector(
                      onTap: () {
                        setState(() {
                          selRes = luogo;
                        });
                      },
                      child: Container(
                        width: 180,
                        margin: const EdgeInsets.symmetric(horizontal: 8),
                        padding: const EdgeInsets.all(12),
                        decoration: BoxDecoration(
                          color: isSelected ? Colors.red[100] : Colors.white,
                          border: Border.all(
                            color: isSelected ? AppColors.red : Colors.grey,
                            width: 2,
                          ),
                          borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                        ),
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            if (luogo.immagine.isNotEmpty)
                              CachedNetworkImage(
                                imageUrl: luogo.immagine,
                                height:80,
                                width:120,
                                fit: BoxFit.fill,
                                placeholder: (context, url) => CircularProgressIndicator(),
                                errorWidget: (context, url, error) => Icon(Icons.broken_image, size: 80),
                              ),
                            const SizedBox(height: 8),
                            Text(luogo.nome, style: FontStyles.cardTitle),
                            Text("${distanza.toStringAsFixed(0)} m", style: FontStyles.cardText),
                          ],
                        ),
                      ),
                    );
                  }).toList(),
                ),
              ),
            ),
            actions: [
              Row(
                children: [
                  ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      backgroundColor: AppColors.red,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                      ),
                    ),
                    onPressed: () {
                      Navigator.of(context).pop(); // chiude senza selezione
                    },
                    child: const Text('Annulla', style: FontStyles.buttonTextWhite),
                  ),
                  const SizedBox(width: Sizes.smallPaddingSpace),
                  ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      backgroundColor: AppColors.red,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                      ),
                    ),
                    onPressed: selRes != null
                        ? () {
                            Navigator.of(context).pop(selRes); // restituisce il ristorante selezionato
                          }
                        : null,
                    child: const Text('Conferma', style: FontStyles.buttonTextWhite),
                  ),
                ],
              )
            ],
          );
        },
      );
    },
  );

  // se il risultato non è nullo, imposta il ristorante selezionato
  if (result != null) {
    setState(() {
      selectedRestaurant = result;
    });
  }
}


}