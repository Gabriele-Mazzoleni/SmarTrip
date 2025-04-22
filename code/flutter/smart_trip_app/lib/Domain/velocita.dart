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
}
