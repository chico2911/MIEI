using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Stripepay
    {
        public String key { get; set; }
        public long amount {  get; set; }
        public String IDEx { get; set; }

        public String IDC { get; set; }

        public String valido { get; set; }
        public String token { get; set; }
    }
}