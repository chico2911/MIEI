using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using RestSharp;
using Server.Models;

namespace Server.Controllers
{
    public class ImagesController : ApiController
    {
        int versionrequest = 0;
        // GET: api/CityMonuments/5

        // POST: api/CityMonuments
        public JObject Post([FromBody]Tipo type)
        {
            ListObjects lst = new ListObjects();
            ListVersions ls = new ListVersions();
            lst.items = new List<Places>();
            Jwtmodel jwt = new Jwtmodel();
            int valtok = jwt.jwtCheck(type.token);

            if (valtok == 1)
            {

                // if (jwt.jwtCheck(type.token) == 1) ;
                if (type.tipo == "v")
                {
                    

                    BD baseDados = new BD();
                    int x = baseDados.ControloVersoes(type.date);
                    JObject jso = new JObject();


                    jso["valido"] = JToken.FromObject(x);
                    return jso;

                }
                if (type.tipo == "r")
                {
                    BD baseDados = new BD();
                    lst.items = baseDados.ImagesRestaurants(type.cidade, type.date);
                }
                if (type.tipo == "h")
                {
                    BD baseDados = new BD();
                    lst.items = baseDados.ImagesHotels(type.cidade, type.date);
                }
                if (type.tipo == "p")
                {
                    BD baseDados = new BD();
                    lst.items = baseDados.ImagesInterestPoints(type.cidade, type.date);
                }


                string token = jwt.jwtcreate();
                JObject jsons = new JObject();
                jsons["valido"] = JToken.FromObject("1");
                jsons["token"] = JToken.FromObject(token);
                jsons["cenas"] = JToken.FromObject(lst.items);
                return jsons;

            }
            else
            { 
                JObject jsons = new JObject();
                jsons["valido"] = JToken.FromObject("0");
                return jsons;
            }

            /*
             * 
             * 
             * 
             * 
             * 
             * 
             * 
             */
        }

       
    }
}
