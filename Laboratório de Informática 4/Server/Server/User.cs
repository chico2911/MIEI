using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public abstract class User
    {
        public String Nome { get; set; }
        public String Email { get; set; }
        public String Primeiro_nome { get; set; }
        public String Segundo_nome { get; set; }
        public String Pass { get; set; }
        public int Guia { get; set; }
    }
}