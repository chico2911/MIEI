using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class PontoInteresse
    {
        public int Id { get; set; }
        public String Nome { get; set; }
        public Tuple<float,float> Localizacao { get; set; }
        public List<Comentario> Comentarios { get; set; }
        public HashSet<String> Tags { get; set; }
    }
}