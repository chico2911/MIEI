using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Places
    {
       public string url { get; set; }
       public string sitio { get; set; }
       public string descrição { get; set; }
       public string id { get; set; }
       public string classificacao { get; set; }
       public string date { get; set; }

       public string morada { get; set; }

       public string horario { get; set; }

        public string mail { get; set; }
        public string telefone { get; set; }
        public string telemovel { get; set; }
    }

    public class ListObjects
    {
        public List<Places> items { get; set; }
    }
}