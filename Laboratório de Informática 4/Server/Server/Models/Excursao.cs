using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Excursao
    {
        public String Id { get; set; }
        public String Guia { get; set; }
        public String NrRegistados { get; set; }
        public List<String> Clientes { get; set; }
        public String horario { get; set; }
        public String descricao { get; set; }
        public String PontosInteresse { get; set; }

        public String preço { get; set; }

        public String IdExc { get; set; }

        public String token { get; set; }

        public String valido { get; set; }

        public String Cidade { get; set; }
    }

    public class ListExcursões
    {
        public List<Excursao> items { get; set; }
    }
}