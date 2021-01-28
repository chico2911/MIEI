using JWT;
using JWT.Algorithms;
using JWT.Builder;
using JWT.Exceptions;
using Microsoft.AspNetCore.DataProtection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Jwtmodel
    {

        const string secret = "GQDstcKsx0NHjPOuXOYg5MbeJ1XT0uFiwDVvVBrk";





        public string jwtcreate()
        {
            //string SecurityAlgorithm
            var token = new JwtBuilder()
          .WithAlgorithm(new HMACSHA256Algorithm()) // symmetric
          .WithSecret(secret)
          .AddClaim("exp", DateTimeOffset.UtcNow.AddHours(1).ToUnixTimeSeconds())
          .Encode();

            return token;
        }

        internal int jwtCheck(object token)
        {
            throw new NotImplementedException();
        }

        public int jwtCheck(string token)
        {
            try { 
            var json = new JwtBuilder()
        .WithAlgorithm(new HMACSHA256Algorithm()) // symmetric
        .WithSecret(secret)
        .MustVerifySignature()
        .Decode<IDictionary<string, object>>(token);
                return 1;
        }
          catch (Exception e)
            {
                return 0;
            }
            
        }
    }
}