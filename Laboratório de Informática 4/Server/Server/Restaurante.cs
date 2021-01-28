using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Restaurante : PontoInteresse
    {
        public String Morada { get; set; }
        public int NrMesas { get; set; }
        public Horario HorarioFuncionamento { get; set; }
        public int Classificacao { get; set; }
    }
}