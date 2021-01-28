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
    public class EditAcountController : ApiController
    {
        
        // POST: api/EditAcount
        public int Post([FromBody]User user)
        {
            Jwtmodel jwt = new Jwtmodel();
            int valtok = jwt.jwtCheck(user.Token);

            if (valtok == 1)
            {
                BD bd = new BD();
                User us = bd.login(user.Email, user.Pass);
                BD bds = new BD();
                if (us.valido == 1)
                {
                    if (user.valido == 0)
                    {
                        return bds.editPhone(user);

                    }
                    if (user.valido == 1)
                    {
                        return bds.editPass(user);
                    }
                    return -3;
                }
                return -1;
            }
            return 0;

        }

       
    }
}
