using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Admin : User
    {
        List<int> PontosPendentes { get; set; }
    }
}