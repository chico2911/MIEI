using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Guia : User
    {
        public List<int> ExcursoesFeitas { get; set; } //lista dos ids das excursoes
        public Wallet Carteira { get; set; }
        public int Classificacao { get; set; }
    }
}