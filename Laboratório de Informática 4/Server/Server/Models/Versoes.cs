using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Versoes
    {
        public string v_user { get; set; }
        public string v_excursões { get; set; }
        public string v_morada { get; set; }
        public string v_pontointeresse { get; set; }
        public string v_contactos { get; set; }

        public string v_hoteis { get; set; }

        public string v_restaurantes { get; set; }

        public string v_classificacaopontointeresse { get; set; }

        public string v_classificacaohoteis { get; set; }

        public string v_classificacaorestaurantes { get; set; }

        public string v_inscricao { get; set; }
    }

    public class ListVersions
    {
        public List<Versoes> items { get; set; }
    }


}