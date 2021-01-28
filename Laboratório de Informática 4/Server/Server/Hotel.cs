using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Hotel : PontoInteresse
    {
        public String Morada { get; set; }
        public int NrQuartos { get; set; }
        public Horario HorarioFuncionamento { get; set; }
        public int Classificacao { get; set; }
    }

}