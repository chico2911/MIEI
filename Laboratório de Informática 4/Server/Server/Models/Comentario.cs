using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Comentario
    {
        public String Texto { get; set; }
        public String Utilizador { get; set; }
        public int Votacao { get; set; }
        public int tipo { get; set; }
        public int id { get; set; }
        public string Token { get; set; }
        public int valido { get; set; }
    }
    public class ListComments
    {
        public List<Comentario> items { get; set; }
    }
}