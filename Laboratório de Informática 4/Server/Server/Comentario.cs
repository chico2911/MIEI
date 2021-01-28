using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Comentario
    {
        public String Texto { get; set; }
        public int Utilizador { get; set; }
        public int Votacao { get; set; }
    }
}