enum VelocitaSpostamenti {
  rilassata,
  media,
  rapida,
}

extension VelocitaSpostamentiExtension on VelocitaSpostamenti {
  String get label {
    switch (this) {
      case VelocitaSpostamenti.rilassata:
        return 'rilassata';
      case VelocitaSpostamenti.media:
        return 'media';
      case VelocitaSpostamenti.rapida:
        return 'rapida';
    }
  }


  double get valore{
    switch (this) {
      case VelocitaSpostamenti.rilassata:
        return 1.0;
      case VelocitaSpostamenti.media:
        return 1.5;
      case VelocitaSpostamenti.rapida:
        return 2.0;
    }
  }
}
