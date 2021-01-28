using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Excursao
    {
        public int Id { get; set; }
        public int Guia { get; set; }
        public int NrRegistados { get; set; }
        public List<int> Clientes { get; set; }
        public Horario Horario { get; set; }
        public List<int> PontosInteresse { get; set; }
    }
}