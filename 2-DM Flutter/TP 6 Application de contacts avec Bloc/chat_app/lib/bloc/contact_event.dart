abstract class ContactEvent{}
  //Les evenement qui vont etre declanché par l'utilisateur
  //Evenement sous forme de classes et on peut les faire sous forme d'enumerations
  class LoadAllContactsEvent extends ContactEvent{}
  class LoadBDCCContactsEvent extends ContactEvent{}
  class LoadGLSIDContactsEvent extends ContactEvent{}
