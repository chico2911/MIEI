using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using Stripe;
using MySql.Data.MySqlClient;
using Server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Server.Controllers
{
    public class ExcursõesController : ApiController
    {
       
        public int Post(Excursao exc)
        {
            Jwtmodel jwt = new Jwtmodel();
            int tokenstatus = jwt.jwtCheck(exc.token);
            if (tokenstatus == 1)
            {
                BD bd = new BD();
                bd.addExcursao(exc);
                return 1;
            }
            return 0;
        }

       
      
    }
}
