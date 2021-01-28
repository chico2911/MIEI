using Newtonsoft.Json.Linq;
using Server.Models;
using Stripe;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Server.Controllers
{
    public class PayoutController : ApiController
    {
        public JObject Post(Excursao exc)
        {
            JObject jsons = new JObject();
            Jwtmodel jwt = new Jwtmodel();
            int valtok = jwt.jwtCheck(exc.token);

            if (valtok == 1)
            {
                BD bd = new BD();
                string iban = exc.descricao;
                double total = bd.payout(exc.Guia);
                string token = jwt.jwtcreate();
                
                jsons["token"] = JToken.FromObject(token);
                if (double.Parse(exc.preço) <=total) { 
               
                    BD newbd = new BD();
                    newbd.updatesaldo(exc.Guia, total- double.Parse(exc.preço));
                    BD bd2 = new BD();
                    bd2.payrequest(exc.descricao, exc.preço, exc.Guia);
                }
                else
                {   jsons["valido"] = JToken.FromObject(-3);
                    return jsons;
                }
                jsons["valido"] = JToken.FromObject(1);
                return jsons;
            }
            else
            {
                jsons["valido"] = JToken.FromObject(0);
                return jsons;
            }
        }

    }
}
